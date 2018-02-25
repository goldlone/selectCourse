<template>
  <div>

    <el-tabs v-model="activeName">
      <el-tab-pane v-for="s in stages" v-bind:label="s.label" v-bind:name="s.label">
        <el-row :gutter="5">
          <el-col :span="12" v-for="c in s.classes"  type="flex">
            <el-card class="box-card" style="margin-top:20px;">
              <div slot="header" class="clearfix">
                <span>{{c.name}}</span>
                <el-button style="float: right; padding: 3px 0" type="text" :key="c.no" v-on:click="searchClass(c.no,c.stage)">查询此课程的选课状况</el-button>
              </div>
              <div>
                <p>教室:{{c.classroom}}</p>
                <p>授课教师:{{c.teacher}}</p>
                <!--<p>开课时间:{{c.time}}</p>-->
                <p>期数:{{c.stage}}</p>
                <p>开始上课时间:{{c.startDateTime}}</p>
                <p>结束上课时间:{{c.endDateTime}}</p>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-tab-pane>
    </el-tabs>

    <!--<el-row :gutter="5">-->
      <!--<el-col :span="12" v-for="c in classes"  type="flex">-->
        <!--<el-card class="box-card" style="margin-top:20px;">-->
          <!--<div slot="header" class="clearfix">-->
            <!--<span>{{c.name}}</span>-->
            <!--<el-button style="float: right; padding: 3px 0" type="text" :key="c.no" v-on:click=""></el-button>-->
          <!--</div>-->
          <!--<div>-->
            <!--<p>教室:{{c.classroom}}</p>-->
            <!--<p>授课教师:{{c.teacher}}</p>-->
            <!--<p>开课时间:{{c.time}}</p>-->
            <!--<p>期数:{{c.stage}}</p>-->
            <!--<p>开始选课时间:{{c.startDateTime}}</p>-->
            <!--<p>结束选课时间:{{c.endDateTime}}</p>-->
          <!--</div>-->
        <!--</el-card>-->
      <!--</el-col>-->
    <!--</el-row>-->
    <!--座位显示页面-->
    <el-dialog title="座位状况" :visible.sync="chooseSeat">
      <el-row :gutter="10">
        <div class="seat" v-for="a in seats">
          <button class="innerSeat" v-if="a.seatAble"></button>
          <button class="disableSeat" v-if="!a.seatAble" disabled></button>
        </div>
        <div>
          <!--<el-button @click="openDetail()">查看详细座位状况</el-button>-->
          <el-button @click="downloadSeat()">下载座位位次表</el-button>
        </div>
      </el-row>
    </el-dialog>

    <el-dialog title="查看详细座位状况" :visible.sync="detailSeat">
    </el-dialog>
  </div>
</template>

<script>
    let classArray = [];//用来存放课程的数组
    let maxStage = 0;//用来存放最大课程期数的变量
    let config = require("../../config/config");
    const ip = config.ip;
    let moment = require("moment");
    let $ = require("jquery");
    let _ = require("underscore");

  /**
   * 以下是seat类的定义
   */
    class Seat{
      constructor(x,y){
        this.x = x;
        this.y = y;
        this.seatAble = true;
        this.hasBeenChoose = false;
      }
      getPosition(){
        return{
          x:this.x,
          y:this.y
        }
      }
      setSeatAble(state){
        this.seatAble = false;
      }
      setHasBeenChoose(){
        this.hasBeenChoose = true;
      }
      getSeatAble(){
        return this.seatAble
      }
    }


    function getHasBeenChoosedSeats(courseNo,stage){
      /**
       *    获取到已经被选择的座位
       * */
      return new Promise((resolve,reject)=>{
        $.post(`http://${ip}/course/seatStatus`,{
          courseNo:courseNo,
          stage:stage
        },function (response) {
          if(response.code == 1001){
            resolve(response.data);
          }else{
            reject(response.msg);
          }
        })
      })
    }


    let map = [];//一个临时转储变量
    export default {
        name: "select-seats",
        data(){
          return{
            chooseSeat:false,
            beenChooseClass:"",
            beenChooseStage:"",
            classes:[],
            seats:[],//保存座位的数组
            classes:[

            ],
            activeName:"第1期",
            detailSeat:false,//查看详细座位状况
            stages:[]
          }
        },
      methods: {

        /**
         * 下载座位信息表
         */
        downloadSeat(){
          let self = this;
          window.location.href = `http://${ip}/course/exportSelectStatus/?courseNo=${self.beenChooseClass}&stage=${self.beenChooseStage}`;
        },
          /**
           * 查看详细座位状况
           * */
          openDetail(){
            this.detailSeat = true;
          },
        /**
         *  获取课程列表
         *  @returns {null}
         */
        getClassLists() {
          let self = this;
          let result;
          $.post(`http://${ip}/course/allInfo`,{
            power:4
          },function (response) {
            result = response.data;
            console.log(result)
            result.forEach(function (ele) {
              ele.startDateTime = moment(ele.startDateTime).format("YYYY/MM/D,HH:mm:ss")
              ele.endDateTime = moment(ele.endDateTime).format("YYYY/MM/D,HH:mm:ss")
            })
            self.classes = result;
            self.classes = response.data;
            maxStage = result[0].stage;
            for(let i in result){
              if(i.stage>maxStage){
                maxStage = i.stage;//求出最大的期数
              }
            }
            for(let j = 0;j<maxStage;j++){
              self.stages.push({
                label:`第${j+1}期`,
                name:j+1,
                classes:[]
              })
            }
            // let flag = 0;//计数
            setTimeout(function () {

              for(let x=0;x<self.classes.length;x++){
                // console.log(x)
                for(let y = 0;y<self.stages.length;y++){
                  // console.log(x.stage);
                  // console.log(self.stages[y].name)
                  if(self.classes[x].stage == self.stages[y].name){

                    self.stages[y].classes.push(
                      self.classes[x]
                    )
                  }
                }
              }
            },100)
          })
        },
        getSpecialClassStudents(no,stage){
          let self = this;
          $.post(`http://${ip}/course/selectStatus`,{
            courseNo:no,
            stage:stage
          },function (response) {
            console.log(response)
          })
        },
        /**
         *
         * @param no 课程编号
         * @param stage 课程期数
         * @returns {null}
         */
        searchClass(no,stage){
          this.buildMap();
          console.log(no);
          let self = this;
          getHasBeenChoosedSeats(no,stage)
            .then(function (row) {
              row.forEach(function (ele) {
                self.seats[ele].setSeatAble();
              })
            }).then(function () {
            self.beenChooseClass = no;
            self.beenChooseStage = stage;
            self.chooseSeat = true;
          }).catch(function (e) {
            self.$message.error("获取座位信息失败");
          })
          this.getSpecialClassStudents(no,stage);
        },

        /**
         *    构建一个全新的地图
          * */
        buildMap() {
          for(let i =0 ;i<352;i++){
            this.seats[i].seatAble = true;
          }
          this.seats[0].setSeatAble();
          this.seats[20].setSeatAble();
          this.seats[21].setSeatAble();
          this .seats[22*2-1].setSeatAble()
          this.seats[1*22].setSeatAble();
          this.seats[11*22].setSeatAble();
          this.seats[11*22+1].setSeatAble();
          this.seats[12*22].setSeatAble();
          this.seats[12*22+2].setSeatAble();
          this.seats[12*22+1].setSeatAble();
          this.seats[13*22].setSeatAble();
          this.seats[13*22+1].setSeatAble();
          this.seats[13*22+2].setSeatAble();
          this.seats[13*22+3].setSeatAble();
          this.seats[14*22].setSeatAble();
          this.seats[14*22+1].setSeatAble();
          this.seats[14*22+2].setSeatAble();
          this.seats[14*22+3].setSeatAble();
          this.seats[14*22+4].setSeatAble();
          this.seats[15*22].setSeatAble();
          this.seats[15*22+1].setSeatAble();
          this.seats[15*22+2].setSeatAble();
          this.seats[15*22+3].setSeatAble();
          this.seats[15*22+4].setSeatAble();
          this.seats[15*22+5].setSeatAble();
        }
      },
      beforeMount:function () {

        /**
         * 在组件挂载前加载课程列表
         */
        if(map.length > 200){
          this.seats = map;
        }else{
          for(let i = 0;i<16;i++){
            for(let j = 0;j<22;j++)
              map.push(new Seat(i,j))
          }
          for(let i = 0;i<16;i++){
            this.seats = map;
          }
        }
        this.getClassLists();


      }
    }
</script>

<style scoped>
  .hasBeenChoose{
    width:80% ;
    height: 90%;
    border:1px solid rgba(0,0,0,0.5);
    margin: 0 auto;
    background-color: lightgrey;
    -webkit-border-radius: 3px;
    -moz-border-radius: 3px;
    border-radius: 3px;
  }
  .disableSeat{
    width:80% ;
    height: 90%;
    border:1px solid rgba(0,0,0,0.2);
    margin: 0 auto;
    background-color:lightgrey;
    -webkit-border-radius: 3px;
    -moz-border-radius: 3px;
    border-radius: 3px;
  }
  .innerSeat:hover{
    transition: background-color 0.3s;
    background-color: rgb(64,158,225);
  }
  .seat{
    width:4.5%;
    /*padding:3px;*/
    /*margin: 5px;*/
    height: 30px;
    /*border:1px solid rgba(0,0,0,0.5);*/
    background:rgba(0,0,0,0);
    display: inline-block;
  }
  .innerSeat{
    width:80% ;
    height: 90%;
    border:1px solid rgba(0,0,0,0.5);
    margin: 0 auto;
    background-color: white;
    -webkit-border-radius: 3px;
    -moz-border-radius: 3px;
    border-radius: 3px;
  }
</style>
