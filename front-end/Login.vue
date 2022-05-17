<template>

  <div id="background">
          <br><br>
    <div style="font-size: xxx-large;font-weight: bold;padding-top: 40px ">
      What are the popular java projects in GitHub?
    </div>

    <div>
            <br><br>
            <br><br>
    </div>
    <el-form class="login-container" label-position="left"
             label-width="0px">

      <h3 class="login_title">SUSTech-CS209A</h3>
<!--      <el-form-item>-->
<!--        <el-input type="text" v-model="loginForm.username"-->
<!--                  auto-complete="off" placeholder="用户名"></el-input>-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-input type="password" v-model="loginForm.password"-->
<!--                  auto-complete="off" placeholder="密码"></el-input>-->
<!--      </el-form-item>-->



      <!--      <input type="checkbox" v-model="use" id="r1"><label for="r1">记住密码</label>-->
      <!--      <br><br>-->

      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="login">Go to see the result</el-button>
      </el-form-item>



    </el-form>


  </div>


</template>


<script>

export default {
  name: 'Login',
  data() {
    return {
      // imgSrc:require('E:\\-\\CS309\\Project\\picture\\login_bg.jpg'),
      loginForm: {
        username: '',
        password: ''
      },
      responseResult: []
    }
  },
  methods: {
    login() {
      var path = this.$route.query.redirect
            this.$router.replace({path: path === '/' || path === undefined ? '/home' : path})
          }

  }
}
</script>

<style>

#background{
  background:url("../assets/bg.jpg");
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;
}




.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 60px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}


</style>
