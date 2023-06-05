<template>
  <div style="width: 100%;">
    <h1 style="text-align: center">Register / Edit Asset</h1>
    <el-form ref="form" :model="form" label-width="160px" style="width: 1200px;margin: 0 auto;">
      <div class="group">
        <el-form-item label="Asset Name :">
          <el-input v-model="form.name" class="input-width"></el-input>
        </el-form-item>
        <el-form-item label="Asset SN :">
          <el-input v-model="form.assetSN" class="input-width" :disabled="true" ></el-input>
        </el-form-item>
      </div>
      <div class="group">
        <el-form-item label="Department :">
          <el-select v-model="form.deptment" placeholder="Select Department" class="input-width">
            <el-option v-for="item in deptData" :value="item" :key="item" :disabled="qu != null"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Location :">
          <el-select v-model="form.location" placeholder="Select Location" class="input-width">
            <el-option v-for="item in locationData" :value="item" :key="item" :disabled="qu != null"></el-option>
          </el-select>
        </el-form-item>
      </div>
      <div class="group">
        <el-form-item label="Asset Group :">
          <el-select v-model="form.group" placeholder="Select Group" class="input-width">
            <el-option v-for="item in groupList" :value="item" :key="item" :disabled="qu != null"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Expired Warranty :">
          <el-col :span="11">
            <el-date-picker type="date" placeholder="Expired Warranty" value-format="yyyy-MM-dd" v-model="form.date1" style="width:300px;"></el-date-picker>
          </el-col>
        </el-form-item>
      </div>
      <el-form-item label="Asset Description :">
        <el-input v-model="form.description"></el-input>
      </el-form-item>
      <div class="img-box">
        <span style="color:gray;">Asset Image :</span>
        <el-button v-on:click="addImg" type="text">Add a new Image</el-button>
      </div>
      <el-form-item style="float: right">
        <el-button type="primary" @click="register">Register / Edit</el-button>
        <el-button v-on:click="close">Close</el-button>
      </el-form-item>
    </el-form>
    <router-view/>
  </div>
</template>

<script>
import {AssetGroupList, DeportmentList, getLocationList} from '../api/main'
import {RegisterAsset, updateAsset} from '../api/RegisterApi'

export default {
  name: 'Register',
  data () {
    return {
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: '',
        assetSN: '',
        deptment: '',
        location: '',
        group: '',
        description: ''
      },
      deptData: [],
      groupList: [],
      locationData: [],
      asserId: '',
      qu: this.$route.query.item
    }
  },
  mounted: function () {
    this.getDeportment()
    this.getAssetGroup()
    this.getLocation()
    this.assignment()
  },
  methods: {
    assignment () {
      let item = this.$route.query.item
      if (item != null) {
        this.form.name = item.assetname
        this.form.assetSN = item.assetSN
        this.form.group = item.assetGroupName
        this.form.deptment = item.deptName
        this.form.description = item.description
        this.form.date1 = item.warranty
        this.form.location = item.locName
        this.asserId = item.assId
        console.log(item)
      } else {
        this.$message.info('Please enter information for registering assets!')
      }
    },
    addImg () {
      console.log(1)
    },
    register () {
      let item = this.$route.query.item
      console.log(item)
      if (item != null) {
        updateAsset({
          assetSN: this.form.assetSN,
          assetName: this.form.name,
          deptName: this.form.deptment,
          assetgroupName: this.form.group,
          description: this.form.description,
          warranty: this.form.date1,
          locationName: this.form.location,
          assetId: this.asserId
        }).then((res) => {
          if (res.data.code === 200) {
            this.$message.success(res.data.message)
            this.$router.push('/main')
          } else {
            this.$message.error(res.data.message)
          }
        })
      } else {
        RegisterAsset({
          assetSN: this.form.assetSN,
          assetName: this.form.name,
          deptName: this.form.deptment,
          assetgroupName: this.form.group,
          description: this.form.description,
          warranty: this.form.date1,
          locationName: this.form.location
        }).then((res) => {
          if (res.data.code === 200) {
            this.$message.success(res.data.message)
            this.$router.push('/main')
          } else {
            this.$message.error(res.data.message)
          }
        })
      }
    },
    getLocation () {
      getLocationList().then((res) => {
        if (res.data.code === 200) {
          this.locationData = res.data.data
        } else {
          this.$message.error(res.data.message)
        }
      })
    },
    getAssetGroup () {
      AssetGroupList().then((res) => {
        if (res.data.code === 200) {
          this.groupList = res.data.data
        } else {
          this.$message.error(res.data.message)
        }
      })
    },
    getDeportment () {
      DeportmentList().then((res) => {
        if (res.data.code === 200) {
          this.deptData = res.data.data
        } else {
          this.$message.error(res.data.message)
        }
      })
    },
    close () {
      if (confirm('Are you sure you want to cancel?')) {
        this.$router.push('/main')
      }
    }
  }
}
</script>

<style scoped>
    .input-width{
      width: 300px;
    }
    .group{
      display: flex;
      align-content: center;
      justify-content: space-between;
    }
    .img-box{
      width: 1200px;
        margin: 0 auto;
        padding-left: 45px;
    }
</style>
