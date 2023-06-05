<template>
  <div>
    <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
      <h3 class="login-title">Login</h3>
      <el-form-item label="Phone:" prop="telephone">
        <el-input type="text" placeholder="Telephone" v-model="form.telephone"/>
      </el-form-item>
      <el-form-item label="Password:" prop="password">
        <el-input type="password" placeholder="Password" v-model="form.password" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" v-on:click="onLogin">Login</el-button>
        <el-button v-on:click="outLogin('loginForm')">Close</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {login} from '../api/Login'
export default {
  name: 'Login',
  data () {
    return {
      form: {
        telephone: '',
        password: ''
      },
      rules: {
        telephone: [{required: true, message: '账号不能为空', trigger: 'blur'}],
        password: [{required: true, message: '密码不能为空', trigger: 'blur'}]
      },
      responseResult: [],
      dialogVisible: false
    }
  },
  methods: {
    onLogin () {
      login({
        phone: this.form.telephone,
        password: this.form.password
      }).then((resp) => {
        let code = resp.data.code
        console.log(code)
        if (code === 200) {
          let data = resp.data.data
          let token = data.token
          let user = data.user
          this.$store.commit('SET_TOKEN', token)
          this.$store.commit('SET_USER', user)
          // console.log(this.$store.state.token)
          var path = this.$route.query.redirect
          console.log(path)
          this.$router.replace({path: path === '/' || path === undefined ? '/main' : path})
        } else {
          this.$message.error('Entered account or password!')
        }
      })
    },
    outLogin (forName) {
      this.$refs[forName].resetFields()
    }
  }
}
</script>

<style scoped>
.login-box {
  border: 1px solid #DCDFE6;
  width: 350px;
  margin: 180px auto;
  padding: 35px 35px 15px 35px;
  border-radius: 5px;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  box-shadow: 0 0 25px #909399;
}

.login-title {
  text-align: center;
  margin: 0 auto 40px auto;
  color: #303133;
}
</style>
