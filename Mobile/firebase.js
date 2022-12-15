import firebase from "firebase/compat/app";
import "firebase/compat/auth";
import { getStorage } from "firebase/storage";

const app = firebase.initializeApp({
  apiKey: "AIzaSyDADusAFsb0081OjSQp1B8rTA6VgGiSXCc",
  authDomain: "auth-test-3aad8.firebaseapp.com",
  databaseURL:
    "https://auth-test-3aad8-default-rtdb.asia-southeast1.firebasedatabase.app",
  projectId: "auth-test-3aad8",
  storageBucket: "auth-test-3aad8.appspot.com",
  messagingSenderId: "712640680215",
  appId: "1:712640680215:web:cf19387609d017f88e6752",
});

export const storage = getStorage(app);

export const auth = app.auth();
export default app;
