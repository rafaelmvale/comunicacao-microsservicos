import { Router } from "express"
import UserController from "../controller/userController.js"

const router = new Router()

router.get("/api/user/email/:email", UserController.findByEMail)
router.post("/api/user/auth", UserController.getAccessToken)

export default router