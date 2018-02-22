let axios = require("axios");
let config = require("../config/configs")
// import QS from 'qs'
let QS = require("qs");
const HEADER = {
  headers:{'Content-Type':'application/x-www-form-urlencode'}
}
let requests = {
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
