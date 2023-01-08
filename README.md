# Goal of the Project:
    The purpose of the project is to simulate a transaction like Bitcoins in a Blockchain.
    You can launch the application where a window will pop up. Inside this one, it is
    possible to make or receive a transaction.
    
# Installation and launch the application
    First of all, make sure that you use Oracle OpenJDK version 18.0.1 instead
    the application won't be able to be launch. Furthermore, fxml is only 
    compatible with the version 18.0.1 of OpenJDK. With that, you will be
    able to open the application but without any connection with a server.
    To be able to have a connection, you will need two machines which are
    both connected to the same network. On the **PeerClient** class, you will set the
    IP address where you want to connect. For exemple:
    - PC1 has the IP address: 192.168.54.45
    - PC2 has the IP address: 192.168.54.142
    That means PC1, in the PeerClient, will write 192.168.54.142
    and the PC2 will write 192.168.54.45.
    
    If you encounter problems when you try to connect to each other, be sure 
    to disable the firewall.
    
    If you want to make a transaction, copy, each, your public key at the bottom
    of the window and share it with the person you want to make
    the transaction with.
    Then click on the "Menu" button, paste the address, type the amount you
    want to sell, send transaction and finish.
    After a little moment, the person will receive the new amount and his
    balance will be updated.
