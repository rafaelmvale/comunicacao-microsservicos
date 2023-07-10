import User from "../model/user.js";


class UserRepository { 
  async findById(id){
    try {
      return await User.findOne({where: id})
    } catch (error) {
      console.log(err.message)
      return null
    }
  }
  async findByEmail(email){
    try {
      console.log(email)
      const usuario = await User.findOne({where: {email}})
      console.log('Busca no repositorio', usuario)
      
      return usuario
    } catch (error) {
      return null
    }
  }
}
export default new UserRepository()