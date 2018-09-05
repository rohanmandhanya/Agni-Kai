#include<GSM.h>
#define PINNUMBER "0000"

GSM gsm_access;
GSM_SMS sms;
String sender_num = "12456778119";

int gaspin = 7;
int heat = 8;
int smoke = 9;
boolean notconnected = true;
void setup() {
  // put your setup code here, to run once:
    Serial.begin(9600);
    pinMode(gaspin, INPUT);
    pinMode(heat, INPUT);
    pinMode(smoke, INPUT);
   while(notconnected)
    {
      if(gsm_access.begin(PINNUMBER)==GSM_READY)
        {
          Serial.print("Connected");
          notconnected = false;
          }
       else
       {
          Serial.print("Not Connected Yet");
        }
        delay(2000);
      }
}
int receive_msg()
{     char sender[10];
    if(sms.available())
        { char c;
               sms.remoteNumber(sender , 10); 
               c = sms.read();
               if(sender_num.compareTo(sender)== 0 && c == '1')
               {
                  sms.flush();
                  return 1;
                }
          }
          else
          {
            return 0;
            }
  }
void loop() {
  // put your main code here, to run repeatedly:
  pinMode(gaspin,HIGH);
  pinMode(heat , HIGH);
  pinMode(smoke , HIGH);
  if(Serial.available()>0)
  {
    int gas = Serial.read();
    int heat1 = Serial.read();
    int smoke1 = Serial.read();
  Serial.print(gas);
  if(/*some valuse according to gas output*/ gas == '1' || heat1 == '1' || smoke1 == '1')
     {
        char msg[] = " This is warning ";
        char mob_num[] = {'9039567614'};
          
        // send sms
        sms.beginSMS(mob_num);
        sms.print(msg);
        sms.endSMS();
        
        delay(500000);//5 min delay
        int n = receive_msg(); // agr msg yess aata h to n==1 nhi aaya to n==0 and sab ko msg chala jaega.
        if( n==0 )
        {char s[] ="999999999";
           char p[] = "777777777";
                  sms.beginSMS(s);
                  sms.print(msg);
                  sms.endSMS();
          
                  sms.beginSMS(s);
                  sms.print(msg);
                  sms.endSMS();
          }
        
       }
  }
}
