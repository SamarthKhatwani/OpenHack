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

  class Firebase{
      constructor(){
          app.initializeApp(config);

          this.auth=app.auth();
      }

      doCreateUserWithEmailAndPassword=(email,password)=>
        this.auth.createUserWithEmailAndPassword(email,password);

      doSignInWithEmailAndPassword=(email, password)=>
        this.auth.signInWithEmailAndPassword(email,password);  

      doSignOut =() => this.auth.signOut();  

      doPasswordReset= email => this.auth.sendPasswordResetEmail(email);

      doPasswordUpdate = password=> 
        this.auth.currentUser.updatePassword(password);
  }

export default Firebase;