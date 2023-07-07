import userService from "../service/userService.js";


class UserController {
  async getAccessToken(req, res){
    let accessToken = await userService.getAccessToken(req)
    return res.status(accessToken.status).json(accessToken)
  }
  async findByEMail(req, res){
    let user = await userService.findByEmail(req)

    return res.status(user.status).json(user)
  }
}
export default new UserController()