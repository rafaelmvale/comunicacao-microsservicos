import userRepository from "../repository/userRepository.js"
import * as httpStatus from '../../../config/contants/httpStatus.js'
import * as secrets from '../../../config/contants/secrets.js'
import UserException from "../exception/UserException.js";
import bcrypt from 'bcrypt'
import jwt from 'jsonwebtoken'


class UserService {
  async findByEmail(req){
    try {
      const { email } = req.params;
      const { authUser } = req;
      this.validateRequestData(email)
      let user = await userRepository.findByEmail(email);
      this.validateUserNotFound(user)
      this.validateAuthenticateUser(user, authUser)
      return { 
        status: httpStatus.SUCCESS,
        user: {
          id: user.id, 
          name: user.name,
          email: user.email
        }
      }
    } catch (error) {
      return {
        status: error.status ? error.status : httpStatus.INTERNAL_SERVER_ERROR,
        message: error.status
      }
    }
  }

  validateRequestData(email){
    if(!email) {
      throw new UserException(httpStatus.BAD_REQUEST,"User email was not informed")
    }
  }


  validateUserNotFound(user){
    if(!user){
      throw new Error(httpStatus.BAD_REQUEST, "User was not found")
    }
  }

  validateAuthenticateUser(user, authUser){
    if(!authUser || user.id !== authUser.id){
      throw new UserException(httpStatus.FORBIDDEN, "You cannot see this user data")
    }
  }

  async getAccessToken(req){

    try {
      
      const { email, password } = req.body
      this.validateAccessTokenDate(email, password)
      let user = await userRepository.findByEmail(email)
      this.validateUserNotFound(user)
      await this.validatePassword(password, user.password)
      const authUser = { id: user.id, name: user.name, email: user.email}
      const accessToken = jwt.sign({ authUser}, secrets.API_SECRET, {
        expiresIn: '1d'
      });
      return {
        status: httpStatus.SUCCESS,
        accessToken,
      }
    } catch (error) {
      return {
        status: error.status ? error.status : httpStatus.INTERNAL_SERVER_ERROR,
        message: error.status
      }
    }


  }

  validateAccessTokenDate(email, password){
    if(!email || !password) {
      throw new UserException(httpStatus.UNAUTHORIZED, "Email and password must be informed")
    }
  }

  async validatePassword(password, hashPassword){
    if(!await bcrypt.compare(password, hashPassword)){
      throw new UserException(httpStatus.UNAUTHORIZED, "Password doesn't match")
    }
  }
}

export default new UserService()