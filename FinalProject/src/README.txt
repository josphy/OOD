Local Test Instruction:
Start the game:
1 launch the (client) Chat App controller (regularMainController.launch)
2 launch the (server) Game Server controller (ServerController.launch)
3 Input the server's IP address into the ChatApp and connect. The client will receive a lobby room and a team room.
4 When the server click "send map", and the map will be sent through lobby chat room.
5 When the server click "start game", the client will start to receive instruction in both lobby and the game view. 

Game operations:
6 There are a couple of annotations on the map, each one represents a US state.
7 A random state name will be sent to players.
8 Please click the annotation which is inside the state which corresponds to the give state name.
9 If any of your team gets the right answer fastest, your team will get one score.
10 When any player gets the right answer, the game proceeds to the next round.
11 After answering all the questions, the game is over. The team with the highest score will be the winner.

-------------------------------------------

Specific Implementation of the Common API:
- Client chat app's IUser implementation is User. Server chat app's IUser implementation is Server, which will send invitation to rooms in auto-connect-back.
- Upon receiving IInvitationType data in User, automatically join without client agreement or manual joining.
- Upon receiving IAddReceiverType in the chat room, we follow the FAQ specified protocol. 
  The receiver will check if the data contains a receiver stub new to him. If new, echo the IAddReceiverType packet to the room. Also add the sender.
- Mixed Data Dictionary is shared per application. (That's what I intended, but I am not sure if the dictionary is actually passed to mini model by reference or simply copied over.)