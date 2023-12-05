import mongoose from "mongoose";

import { MONGO_DB_URL } from '../contants/secrets.js'


export function connectMongoDB() {
  mongoose.connect(MONGO_DB_URL, { useNewUrlParser: true, serverSelectionTimeoutMS: 180000,})
  

  mongoose.connection.on('connected', function () {
    console.info('The application connected to MongoDB sucessfully');
  });

  mongoose.connection.on('error',function () {
    console.error('The application failed to connect to MongoDB');
  });
}