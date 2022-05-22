
<template>
  <el-container>
    <el-header height="63px">
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
        <span style="color: white; position: absolute;padding-top: 16px;right: 50%;font-size: 25px;font-weight: bold">SUSTech - Github</span>
        <el-input v-model="searchValue" placeholder="Key"
                  suffix-icon="el-icon-search"
                  size="medium"
                  style="width: 300px;position:absolute;right: 20%;margin-top: 12px"></el-input>

        <el-button size="small" style="position:absolute;right: 14%;margin-top: 13px" @click.native="searchName">
          Search
        </el-button>

      </el-menu>

    </el-header>
    <el-container>
      <el-aside width="150px"><el-menu
        :default-active="$route.path"
        router
        background-color="white"
        text-color="#222"
        active-text-color="red">
        <span style="font-size: 18px;font-weight: bold">Recommend</span>
        <el-menu-item @click.native="searchTag(item)" v-for="(item,i) in tagList" :key="i" >
          {{ item.tagItem }}
        </el-menu-item>
      </el-menu></el-aside>
      <el-main>
        <el-image :src="localImage" style="height: 380px; width: 900px; position: relative;padding-left: 10px">
        </el-image>
        <div style="position: relative;font-size: 25px;font-weight: bold;top: 10px">Recommend project：</div>
        <div style="position: relative;top: 10px;left:20px;">
          <el-row>
            <el-col :span="4" v-for="(item, index) in recommend" :key="item.id"  :offset="1">
              <el-card shadow="hover" style="width: 230px;height: 220px;" @click.native="checkDetails(item)">
                <div style="padding: 10px;height: 310px;">

                  <div>Name: {{item.name}}</div>
                  <br>
                  <br>
                  <div>star: {{item.star}}</div>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </div>
      </el-main>

    </el-container>
  </el-container>
</template>


<script>
import axios from "axios";
import Vue from "vue";

export default {
  name: 'home',
  data() {
    return {
      localImage: require("../assets/home.jpg"),
      searchValue: '',
      passwordVisible: false,
      p1: 'https://img-u-3.51miz.com/Templet/00/19/52/19/195219_c3e8071348f03e0b00338825d8d4992d.jpg-0.jpg',
      p2: 'http://p.moimg.net/project/project_20180502_1525258786_7271.jpg?imageMogr2/auto-orient/strip',
      navList: [
        {name: '/home', navItem: 'Home'},
        {name: '/collection', navItem: 'Collection'},
        // {name: '/personal', navItem: '个人中心'}
      ],
      searchItems: [],
      state2: '',
      recommend: [],
      tagList:[
        {url: '/result', tagItem:'Android', value:'android'},
        {url: '/result', tagItem:'Spring-boot', value:'spring-boot'},
        {url: '/result', tagItem:'Spring', value:'spring'},
        {url: '/result', tagItem:'Hacktoberfest', value:'hacktoberfest'},
        {url: '/result', tagItem:'Kotlin', value:'kotlin'},
        {url: '/result', tagItem:'Android-library', value: 'android-library'},
        {url: '/result', tagItem:'Database', value: 'database'},
        {url: '/result', tagItem:'Javascript', value: 'javascript'},
        {url: '/result', tagItem:'Mysql', value: 'mysql'}
      ]
    };
  },
  created() {

    axios.get('http://localhost:8443/projectTop8').then(res =>{
      this.recommend = res.data;
    })
  },
  methods: {
    checkDetails(item){
      this.$router.push({name: 'Detail', query: {goods_name: item.goods.goods_name, picture: item.goods.picture,
          goods_id: item.goods.goods_id, price: item.goods.price, detail: item.goods.detail, restNumber: item.goods.rest_num, sellerID: item.goods.seller_id, comment: item.comments, imgList:item.imgs}})
    },
    exit() {
      axios.post('/logout', {
        username: ''
      }).then(function (resp) {
        alert('successful')
      })
      this.$router.push('/login');
    },
    personalHomepage() {
      this.$router.push('/personal');
    },
    querySearch(queryString, cb) {
      var searchItems = this.searchItems;
      var results = queryString ? searchItems.filter(this.createFilter(queryString)) : searchItems;
      cb(results);
    },
    createFilter(queryString) {
      return (searchItem) => {
        return (searchItem.value.toLowerCase().indexOf(queryString.toLowerCase()) > -1);
      };
    },
    loadAll() {
      return [
      ];
    },
    searchTag(item){
      this.$router.push({path: '/result', query: {value: item.value, tag: 1}});
    },
    searchName(){
      this.$router.push({path: '/result', query: {value: this.searchValue, tag: 0}});
    }
  },
  mounted() {
    this.searchItems = this.loadAll();
  }
}
</script>


<style scoped>
a{
  text-decoration: none;
}

span {
  pointer-events: none;
}
.i {
  float: right;
}

</style>
