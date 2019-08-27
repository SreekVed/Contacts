# Contacts for Android

I made this Android app which can be used to store the user's contacts. It uses a scrollable ListView and ArrayAdapter to display the user's contacts. The list of saved contacts are loaded from the device's memory, if available.

The user can add contacts and specify their phone numbers and addresses by pressing the plus icon. The app implements the Serializable interface to pass objects between Activities via intents.

Once a contact is added, it is written to the phone's internal storage and is permanently stored until the user decides to delete the contact. This is done by writing the raw binary data from the HashMaps to a savefile. This ensures that the saved contacts are not lost each time the app is closed.

When the user chooses to view a contact, the app presents the options to dial the number, send a text message to the contact, and to delete the contact. The phone number is formatted as per the user's locale using PhoneNumberUtils.

## Screenshots :

![](/screenshots/list.png?raw=true) 
![](/screenshots/add.png?raw=true)
![](/screenshots/1.png?raw=true)
![](/screenshots/2.png?raw=true)
![](/screenshots/3.png?raw=true)
![](/screenshots/dial.png?raw=true)
![](/screenshots/sms.png?raw=true)
