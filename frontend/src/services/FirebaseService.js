import app from 'firebase/app';
import 'firebase/auth';

const config = {
  apiKey: "AIzaSyAx1YRQ9IAl8rlAMmhnj8IS_0Msz2C6ios",
  authDomain: "cmpe-256-open-hack.firebaseapp.com",
  databaseURL: "https://cmpe-256-open-hack.firebaseio.com",
  projectId: "cmpe-256-open-hack",
  storageBucket: "cmpe-256-open-hack.appspot.com",
  messagingSenderId: "335943792546"
};

export default class Firebase {

  static instance = null;

  /** Singleton Pattern
    * @returns {Firebase}
    */
  static getInstance() {
    if (Firebase.instance == null) {
      Firebase.instance = new Firebase();
    }
    return this.instance;
  }

  constructor() {
    app.initializeApp(config);
    this.auth = app.auth();
    this.googleProvider = new app.auth.GoogleAuthProvider();
  }



  signUp(email, password){
    return this.auth.createUserWithEmailAndPassword(email, password);
  }

  login(email, password){
    return this.auth.signInWithEmailAndPassword(email, password);
  }

  signOut(){
    return this.auth.signOut();
  }
  
  signInWithGoogle(){
    return this.auth.signInWithPopup(this.googleProvider);
  }

  // doPasswordReset = email => this.auth.sendPasswordResetEmail(email);

  // doPasswordUpdate = password =>
  //   this.auth.currentUser.updatePassword(password);
   
}
