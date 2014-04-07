#include <SPI.h>
#include <Ethernet.h>
#include <SD.h>

File webFile;

int luzArea = 2;
int luzBanheiro = 5;
int luzCozinha = 6;
int luzSala = 8;

int pos = 0; 
byte mac[] = { 
  0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };   //physical mac address
byte ip[] = { 
  192, 168, 10, 20 };                      // ip in lan (that's what you need to use in your browser. ("192.168.1.178")
byte gateway[] = { 
  //192, 168, 28, 254 };                   // internet access via router
  192, 168, 10, 1 };
byte subnet[] = { 
  //255, 255, 254, 0 };                  //subnet mask
  255, 255, 255, 0 };                  //subnet mask
EthernetServer server(80);                             //server port     
String readString;

void setup() {
  // Open serial communications and wait for port to open:
  Serial.begin(9600);
  while (!Serial) {
    ; // wait for serial port to connect. Needed for Leonardo only
  }
  
  // inicializa saidas
  pinMode(luzArea, OUTPUT);
  pinMode(luzBanheiro, OUTPUT);
  pinMode(luzCozinha, OUTPUT);
  pinMode(luzSala, OUTPUT);  
  
  // start the Ethernet connection and the server:
  Ethernet.begin(mac, ip, gateway, subnet);
  server.begin();
  Serial.print("server is at ");
  Serial.println(Ethernet.localIP());

  // initialize SD card
  Serial.println("Initializing SD card...");
//  pinMode(10, OUTPUT);
  if (!SD.begin(4)) {
    Serial.println("ERROR - SD card initialization failed!");
    return;    // init failed
  }
  Serial.println("SUCCESS - SD card initialized.");
  // check for index.htm file
  if (!SD.exists("index.txt")) {
    Serial.println("ERROR - Can't find index.txt file!");
    return;  // can't find index file
  }
  Serial.println("SUCCESS - Found index.txt file.");
}


void loop() {
  // Create a client connection
  EthernetClient client = server.available();
  if (client) {
    while (client.connected()) {   
      if (client.available()) {
        char c = client.read();

        //read char by char HTTP request
        if (readString.length() < 100) {
          //store characters to string
          readString += c;
          Serial.print(c);
        }

        //if HTTP request has ended
        if (c == '\n') {          
          Serial.println(readString); //print to serial monitor for debuging

          client.println("HTTP/1.1 200 OK"); //send new page
          client.println("Content-Type: text/html");
          client.println();              
          // send web page
          webFile = SD.open("index.txt");        // open web page file
          if (webFile) {
              while(webFile.available()) {
                  client.write(webFile.read()); // send web page to client
              }
              webFile.close();
          }
          delay(1);
          //stopping client
          client.stop();
          //controls the Arduino if you press the buttons
          Serial.println(readString);
          
          // luzArea
          if (readString.indexOf("pin2on") >0){
            digitalWrite(luzArea, HIGH);
            Serial.println("luzArea acender");
          } 
          if (readString.indexOf("pin2off") >0){ 
            digitalWrite(luzArea, LOW);
            Serial.println("luzArea apagar");
          }          

          // luzBanheiro
          if (readString.indexOf("pin5on") >0){
            digitalWrite(luzBanheiro, HIGH);
            Serial.println("luzBanheiro acender");
          } 
          if (readString.indexOf("pin5off") >0){ 
            digitalWrite(luzBanheiro, LOW);
            Serial.println("luzBanheiro apagar");
          }    

          // luzCozinha
          if (readString.indexOf("pin6on") >0){
            digitalWrite(luzCozinha, HIGH);
            Serial.println("luzCozinha acender");
          } 
          if (readString.indexOf("pin6off") >0){ 
            digitalWrite(luzCozinha, LOW);
            Serial.println("luzCozinha apagar");
          }    

          // luzSala
          if (readString.indexOf("pin8on") >0){
            digitalWrite(luzSala, HIGH);
            Serial.println("luzSala acender");
          } 
          if (readString.indexOf("pin8off") >0){ 
            digitalWrite(luzSala, LOW);
            Serial.println("luzSala apagar");
          }              
          //clearing string for next read
          readString="";  

        }
      }
    }
  }
}





