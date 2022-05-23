
<template>
  <el-container>
<!--    <el-header height="63px">-->
      <el-menu
        :default-active="$route.path"
        router
        mode="horizontal"
        background-color="#1F2D3D"
        text-color="white"
        active-text-color="red"
        style="min-width: 1200px">
        <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
          {{ item.navItem }}
        </el-menu-item>
        <el-input v-model="searchValue" placeholder="Key"
                  suffix-icon="el-icon-search"
                  size="medium"
                  style="width: 300px;position:absolute;right: 20%;margin-top: 12px"></el-input>
        <el-button size="small" style="position:absolute;right: 14%;margin-top: 13px" @click.native="searchName">
          Search
        </el-button>
      </el-menu>
<!--    </el-header>-->
    <el-container>
      <el-main>
        <span style="color: black;padding-top: 15px;font-size: 15px;font-weight: bold">Sort: <span v-html="'\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0'"></span></span>
        <el-radio-group v-model="sort">
          <el-radio :label="1">Star</el-radio>
          <el-radio :label="2">Fork</el-radio>
          <el-radio :label="3">Watch</el-radio>
          <el-radio :label="4">Issues</el-radio>
        </el-radio-group>
        <el-button size="small" style="color: black; background-color: #ffffff" @click.native="searchName">
          commit
        </el-button>

          <div style="position: relative;top: 20px;left:20px;">
            <el-row>
              <el-col :span="4" v-for="(item) in result" :key="item.id"  :offset="1">
                <el-card shadow="hover" style="width: 250px;height: 320px;" @click.native="checkDetails(item)">
                  <div style="padding: 10px;height: 310px;">
                    <div v-html="highlight(item.name)">{{item.name}}</div>
                    <br>
                    <br>
                    <div class="el-icon-star-on">Star: {{item.star}}</div>
                    <br>
                    <div class="el-icon-tickets">Issues: {{item.issues}}</div>
                    <br>
                    <div class="el-icon-position">Fork: {{item.fork}}</div>
                    <br>
                    <div class="el-icon-view">Watch: {{item.watch}}</div>
                  </div>
                </el-card>
              </el-col>
            </el-row>
           </div>
      </el-main>
    </el-container>
    <el-footer>

    </el-footer>
  </el-container>
</template>


<script>
import axios from "axios";
import Vue from "vue";

export default {
  data() {
    return {
      sort:0,
      picture: "../assets/home.jpg",
      searchValue: '',
      address: '',
      lowest: 0,
      highest: 0,
      number: 1,
      p2: 'http://p.moimg.net/project/project_20180502_1525258786_7271.jpg?imageMogr2/auto-orient/strip',
      navList: [
        {name: '/home', navItem: 'Home'},
        {name: '/collection', navItem: 'Collection'},
      ],
      result: 'cnmd',
      searchTag:''
    };
  },
  created() {
    this.sort = this.$route.query.sort;
    this.searchValue = this.$route.query.value;
    axios.get('http://localhost:8443//projectInfo',{
      params:{
        name: this.searchValue,
        sort: this.sort
      }
    })
    .then(res =>{
      this.result = res.data;
      // for (let i=0;i<this.result.length;i++){
      //   if (this.result[i].name.includes(this.searchValue)){
      //     this.result[i].name = this.result[i].name.replace(this.searchValue, '<span style="color:red; font-weight: bold">'+ this.searchValue +'</span>')
      //   }
      // }
    })
    .catch(function (error){
      this.result = 'error'
    })
  },
  methods: {
    highlight(name){
      let a = name.toLowerCase()
      let b = this.searchValue.toLowerCase()
      if (name.includes(this.searchValue)){
        name = name.replace(this.searchValue, '<font style="color:red!important;">'+ this.searchValue +'</font>')
      }
      return name
    },
    searchName(){
      this.$router.push({name: 'Result', query: {value: this.searchValue, sort: this.sort}});
      this.$router.go(0)
    },
    checkDetails(item){
      this.$router.push({name: 'Detail', query: {name: item.name,
          id: item.id, star: item.star, fork: item.fork, issues: item.issues, watch: item.watch, url:item.url}})
    },
  },

}
</script>


<style scoped>
a{
  text-decoration: none;
}

span {
  pointer-events: none;
}
</style>
