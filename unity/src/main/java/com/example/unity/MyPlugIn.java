package com.example.unity;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MyPlugIn {
    private static final MyPlugIn ourInstance = new MyPlugIn();
    private static final String TAG = "MyPlugIn";
    MQTTHelper mqttHelper;
    Context context;

    public static MyPlugIn getInstance() {
        return ourInstance;
    }

    private MyPlugIn() {
        Log.d(TAG, "MyPlugIn: Created");
    }

    public void setContext (Context unityContext){
        Log.d(TAG, "setContext: set");
        context=unityContext;
        mqttHelper=new MQTTHelper(context);
        connect();
    }

    private void connect(){
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {

            }

            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Log.w("Debug1",message.toString());
                Toast.makeText(context, message.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
//        mqttHelper.subscribeToTopic();
    }

    private void publish(){
            mqttHelper.toPublish();
    }

    private void setTopic(String s){
        mqttHelper.setSubscriptionTopic(s);
        mqttHelper.subscribeToTopic();
        mqttHelper.isOn=false;
    }
}
