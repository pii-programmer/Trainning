package com.example.trainnning

import androidx.annotation.IdRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "iceCream_table")
data class IceCream(
    @PrimaryKey(autoGenerate = true)
    var id: Long?  = null,
    var icon: String?,
    var title: String?,
    var text: String?
)
// 主キーidはautoGenerateになる。よってinsertの際、idを指定しないこと。

// JAVAでEntityを書いてみる
//@Entity
//public class IceCream {  //コンストラクタ
//    @Id
//    private String icon;
//    private String title;
//    private String text;
//}
//
//public string getIcon(){
//    return this.icon;
//}
//
//public void setIcon(string icon){
//    this.icon = icon;
//}