
package com.example.seouler
//package com.example.seouler.APITask

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.location.LocationCallback
import org.json.JSONObject



object VolleyService_weather {
    lateinit var response_json : JSONObject
    //val testUrl = "http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst?serviceKey=I23jHEEvrpNKlQiFjB8me4jV48AF6y2Sj0mGzBX0s4jce9OyTCgYqNi31f6LgQgncOTlVO6Wzal8vv96JHegig%3D%3D&numOfRows=10&pageNo=1&regId=11A00101"
    var testUrl_default = "https://api.openweathermap.org/data/2.5/onecall?appid=64e57257f79fb4616c18f554e9b01140&units=metric"
    var Url = testUrl_default
    var lat = 0.0
    var lon = 0.0
    fun setlatlon(lat : Double, lon : Double){
        this.lat = lat
        this.lon = lon
        Url+= "&lat=$lat&lon=$lon"
    }
    fun testVolley(context: Context, success: (Boolean) -> Unit) {
        println("잘못.. $Url")
        if(Url.contains("lat=-200.0")){
            Url = testUrl_default
        }
        val myJson = JSONObject()
        val requestBody = myJson.toString()
        /* myJson에 아무 데이터도 put 하지 않았기 때문에 requestBody는 "{}" 이다 */

        val testRequest = object : StringRequest(Method.GET, Url , Response.Listener { response ->
            println("서버 Response 수신: $response")
            /*내가만든..... 것....*/

            response_json = JSONObject(response)
            success(true)
        }, Response.ErrorListener { error ->
            Log.d("ERROR", "서버 Response 가져오기 실패: $error")
            success(false)
        }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }
            /* getBodyContextType에서는 요청에 포함할 데이터 형식을 지정한다.
             * getBody에서는 요청에 JSON이나 String이 아닌 ByteArray가 필요하므로, 타입을 변경한다. */
        }

        Volley.newRequestQueue(context).add(testRequest)
    }
}