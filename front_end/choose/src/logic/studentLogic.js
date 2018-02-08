let axios = require("axios");
let config = require("../config/configs")
import QS from 'qs'
let requests = {
  /**
   * 获取所有学生的信息
   * @returns {Promise<any>}
   */
  getAllInfo:function(){
    return new Promise((resolve,reject)=>{
      axios.post(config.ip+"/stu/allInfo")
        .then(response=>{
          console.log(response)
        }).catch(err=>{

        })
    })
  },
  /**
   * 获取某个基层党组织的所有学生信息
   * @returns {Promise<any>}
   */
  getSchoolInfo:function (schoolNumber) {
    return new Promise((resolve,reject)=>{
      axios.post(config.ip + "./stu/schoolInfo",QS.stringfy({
        schoolNo:schoolNumber
      }), {headers:{'Content-Type':'application/x-www-form-urlencoded'}}).then(resoponse=>{

      }).catch(err=>{

      })
    })
  },
  /**
   * 获取某个特定的学生信息
   * @returns {Promise<any>}
   */
  getSpecialStudentInfo:function (stuNo) {
    return new Promise((resolve,reject)=>{
        axios.post(config.ip + "./stu/infoByNo",QS.stringfy({
          stuNo:stuNo
        }), {headers:{'Content-Type':'application/x-www-form-urlencoded'}}).then(response=>{

        }).catch(err=>{

        })
    })
  },
  /**
   * 修改密码
   * @returns {Promise<any>}
   */
  updatePassword:function (stuNo,password,newPassword) {
    return new Promise((resolve,reject)=>{
      axios.post(config.ip + "./stu/updatePassword",QS.stringfy({
        stuNo:stuNo,
        password:password,
        newPassword:newPassword
      }), {headers:{'Content-Type':'application/x-www-form-urlencoded'}}).then(response=>{

      }).catch(err=>{

      })
    })
  },
  /**
   * 管理员修改学员信息
   * @returns {Promise<any>}
   */
  updateInfoByAdmin:function (no,name,schoolNo,age,gender,power) {
    return new Promise((resolve,reject)=>{
      let data ={
        no:no,
        name:name,
        schoolNo:schoolNo,
        age:age,
        gender:gender,
        power:power
      }
      axios.post(config.ip+"/stu/updateInfoByAdmin",QS.stringify(data), {headers:{'Content-Type':'application/x-www-form-urlencoded'}})
        .then(response=>{

        }).catch(err=>{

      })
    })
  },
  /**
   * 学员修改自己的信息
   * @returns {Promise<any>}
   */
  updateInfoBySelf:function () {
    return new Promise((resolve,reject)=>{
      let data = {

        };
      data = QS.stringfy(data);
      axios.post(config.ip+"/",data,
        {
          headers:{'Content-Type':'application/x-www-form-urlencode'}
        }).then(response=>{

        }).catch(err=>{
          console.log(err);
        })
    })
  },
  getCourse(power){
    /**
     * 根据权限获取可选课程列表
     */
    let data = QS.stringfy({
      power:power
    })
    axios.post(`${config.ip}/course/seatStatus`,{
      power:power
    },HEADER)
      .then(response=>{
        console.log(response)
      }).catch(err=>{
      console.log(err)
    })
  }
}
export default requests;
