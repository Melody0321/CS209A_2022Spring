
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
<!--        <el-dropdown style="position: absolute;padding-top: 10px;right: 3%">-->
<!--          <span class="el-dropdown-link">-->
<!--              <el-avatar-->
<!--                :size="40"-->
<!--                :src="headshot"-->
<!--                align="center"-->
<!--                style="border: darkgrey solid 1px"-->
<!--              ></el-avatar>-->
<!--            &lt;!&ndash;            <i class="el-icon-arrow-down el-icon&#45;&#45;right"></i>&ndash;&gt;-->
<!--          </span>-->
<!--          <el-dropdown-menu slot="dropdown">-->
<!--            <el-dropdown-item @click.native="personalHomepage">个人中心</el-dropdown-item>-->
<!--            <el-dropdown-item @click.native="passwordVisible=true">修改密码</el-dropdown-item>-->
<!--            <el-dropdown-item @click.native="exit">退出登录</el-dropdown-item>-->
<!--          </el-dropdown-menu>-->
<!--        </el-dropdown>-->
<!--        <el-autocomplete-->
<!--          class="inline-input"-->
<!--          v-model="searchValue"-->
<!--          :fetch-suggestions="querySearch"-->
<!--          placeholder="请输入内容"-->
<!--          :trigger-on-focus="false"-->
<!--          @select="handleSelect"-->
<!--          prefix-icon="el-icon-search"-->
<!--          size="medium"-->
<!--          style="width: 300px;position: relative;margin-top: 12px"-->
<!--        ></el-autocomplete>-->
        <el-input v-model="searchValue" placeholder="Key"
                  suffix-icon="el-icon-search"
                  size="medium"
                  style="width: 300px;position:absolute;right: 20%;margin-top: 12px"></el-input>
        <el-button style="margin-top: 12px" size="small" @click.native="searchName">
          Search
        </el-button>
      </el-menu>
<!--    </el-header>-->
    <el-container>
      <el-main>
        <span style="color: black;padding-top: 15px;font-size: 15px;font-weight: bold">筛选条件:<span v-html="'\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0'"></span></span>
<!--        <span style="font-size: 10px;font-weight: bold">数量:</span>-->
<!--        <el-input-number v-model="number" :min="0" prop="number" size="small"></el-input-number>-->
<!--        <span v-html="'\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0'"></span>-->
<!--        <span style="font-size: 10px;font-weight: bold">价格区间：</span>-->
<!--        <el-input-number v-model="lowest" :min="0" prop="lowest" size="small"></el-input-number>-->
<!--        <span>-</span>-->
<!--        <el-input-number v-model="highest" :min="lowest" prop="highest" size="small"></el-input-number>-->
<!--        <span v-html="'\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0'"></span>-->
<!--        <el-button size="small" style="color: white; background-color: #2c3e50" @click.native="searchName">-->
<!--          commit-->
<!--        </el-button>-->
          <div style="position: relative;top: 20px;left:20px;">
            <el-row>
              <el-col :span="4" v-for="(item, index) in result" :key="item.id"  :offset="1">
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
      headshot: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
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
    var tag = this.$route.query.tag;
    if (tag === '1'){
      this.searchTag = this.$route.query.value;
    }
    else {
      this.searchValue = this.$route.query.value;
    }
    axios.get('http://localhost:8443//projectInfo',{
      params:{
        name: this.searchValue,
        tag: this.searchTag,

      }
    })
    .then(res =>{
      this.result = res.data;
    })
    .catch(function (error){
      this.result = 'error'
    })
  },
  methods: {
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
    searchName(){
      this.$router.push({name: 'Result', query: {value: this.searchValue, tag: this.tag}});
      this.$router.go(0)
    },
    checkDetails(item){
      this.$router.push({name: 'Detail', query: {goods_name: item.goods.goods_name, picture: item.goods.picture,
          goods_id: item.goods.goods_id, price: item.goods.price, detail: item.goods.detail, restNumber: item.goods.rest_num, sellerID: item.goods.seller_id, comment: item.comments}})
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
    handleSelect(item) {
      console.log(item);
    },
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
</style>
