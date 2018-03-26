import Vuex from 'vuex'
import Vue from 'vue'

Vue.use(Vuex)
export default new Vuex.Store({
    state:{
      studentState:{

      },
      classInfo:{

      },
      power:""
    },
    mutations:{
      setStudentState(studentState){
        this.state.studentState = studentState;
      }
    }
})
