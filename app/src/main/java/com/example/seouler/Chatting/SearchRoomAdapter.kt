package com.example.seouler.Chatting

import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.seouler.R
import com.example.seouler.dataClass.ChattingRoom
import com.example.seouler.dataClass.Message
import kotlinx.android.synthetic.main.rvitem_chattinghome.view.*

class SearchRoomAdapter : RecyclerView.Adapter<SearchRoomHolder>(){
    var listData = ArrayList<ChattingRoom>()
    var userId : Long = 0
    lateinit var searchRoomContext : Context
    var myChattingRoomMessageList = ArrayList<ArrayList<Message>>()

    override fun getItemCount(): Int {
        return listData.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRoomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rvitem_chattinghome, parent, false)
        val holder = SearchRoomHolder(view)
        holder.searchRoomContext = searchRoomContext
        holder.userId = userId
        return holder
    }

    override fun onBindViewHolder(holder: SearchRoomHolder, position: Int) {
        val room = listData.get(position)
        holder.setChattingRoom(room, position)
    }
}
class SearchRoomHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    lateinit var searchRoomContext : Context
    var userId : Long = 0
    fun setChattingRoom(room : ChattingRoom, position : Int){
        itemView.chattingroomCellButton.text = "${room.title}"
        var time : Long = ((System.currentTimeMillis()-room.timestamp)/1000)/60
        var timeMessage : String = ""
        //경과 시간이 60분보다 크다면
        if(time >= 60){
            time /= 60
            if(time >= 24){
                time /= 24
                if(time >= 3){
                    timeMessage = "Long ago"
                }
                else{
                    timeMessage = "${time} day ago"
                }
            }
            else {
                timeMessage = "${time} hour ago"
            }
        }
        else{
            timeMessage = "${time} min ago"
        }
        itemView.chattingroomCellTimestampTextView.text = timeMessage
        itemView.chattingroomCellLocationTextView.text = room.locationName
        itemView.chattingroomCellButton.setOnClickListener {
            var infoIntent = Intent(searchRoomContext, ChattingInfoActivity::class.java)
            infoIntent.putExtra("roomId",room.roomId)
            infoIntent.putExtra(("userId"), userId)
            startActivity(searchRoomContext, infoIntent, null)
            /*
            채팅방 정보를 먼저 띄워줌.
            (참여할 것인지 아닌지 버튼 있음)
            참여를 누르면 채팅방에 참여되었다고 뜨며 db에 participation 항목 추가, 홈화면으로 나가짐.
             */
        }
//        temView.chattingroomCellButton.setOnClickListener {
//            val roomIntent = Intent(chattingHomeContext, ChattingMessageActivity::class.java)
//
//            roomIntent.putExtra("roomId", room.roomId)
//            roomIntent.putExtra("userId", userId)
//            startActivity(chattingHomeContext, roomIntent, null)
//        }
//        itemView.chattingroomCellLocationTextView.text = room.locationName
    }
}