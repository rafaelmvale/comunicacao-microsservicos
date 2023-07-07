import bcrypt from "bcrypt"
import User from "../../modules/user/model/user.js";


export async function createInitialData() {
  await User.sync({force: true});

  let password = await bcrypt.hash('123456', 10)

  await User.create({
    name: "User test",
    email: "teste@teste.com.br",
    password: password
  });
}
