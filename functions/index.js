const functions = require('firebase-functions');
const admin = require('firebase-admin');

admin.initializeApp(functions.config().firebase);

 // Create and Deploy Your First Cloud Functions
 // https://firebase.google.com/docs/functions/write-firebase-functions

 exports.sendNotification = functions.database.ref('/messages/{chatId}/{msgId}')
    .onCreate((snapshot, context) => {
    const chat_id = context.params.chatId;
    const messageFrom = snapshot.val().senderId;
    const user_id = chat_id.replace(messageFrom,'');
   const status =  admin.firestore().collection("users").doc(user_id).get().then(doc => {
    const token = doc.data().deviceToken;
        console.log(token);
        return true;
    })
    return status;
    })
