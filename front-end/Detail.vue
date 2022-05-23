<template>
  <el-container>
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
      <span style="color: white; position: absolute;padding-top: 16px;right: 41%;font-size: 25px;font-weight: bold">SUSTech - Github</span>
    </el-menu>
    <el-container>
      <el-aside width="800px">
      </el-aside>
      <el-main>
        <div>
          <div style="color: #2c3e50; position: absolute;padding-top: 6px;right: 40%;font-size: 30px;font-weight: bold">Project Information:{{yData}}</div>
          <div style="position: absolute;padding-top: 80px;left: 20%;font-weight: bold;font-size: 20px; text-align: right">Name： {{name}}</div>
          <div style="position: absolute;padding-top: 140px;left: 20%;font-weight: bold;font-size: 20px; text-align: right">Star: {{star}}</div>
          <div style="position: absolute;padding-top: 200px;left: 20%;font-weight: bold;font-size: 20px; text-align: left">Issues: {{issues}}</div>
          <div style="position: absolute;padding-top: 260px;left: 20%;font-weight: bold;font-size: 20px; text-align: left">Fork: {{fork}}</div>
          <div style="position: absolute;padding-top: 320px;left: 20%;font-weight: bold;font-size: 20px; text-align: left">Watch: {{watch}}</div>
          <div style="position: absolute;padding-top: 380px;left: 20%;font-weight: bold;font-size: 20px; text-align: left">Create Time: {{timeList[0].create_time}}</div>
          <div style="position: absolute;padding-top: 440px;left: 20%;font-weight: bold;font-size: 20px; text-align: left">Last Modified Time: {{timeList[0].update_time}}</div>
          <div style="position: absolute;padding-top: 500px;left: 20%;font-weight: bold;font-size: 20px; text-align: left">
            <el-tag
              :key="t"
              v-for="t in tagList"
              size="medium"
            >
              {{t.topic}}
            </el-tag>
          </div>


          <div ref="echarts" style="padding-top: 80px; left: 50%;width: 100%; height: 400px"></div>

          <el-button style="position: absolute;left: 45%; top: 88%;background-color: #505458; color: white" @click.native="addCollection">
            Show
          </el-button>

        </div>
      </el-main>
    </el-container>
    <el-footer>

    </el-footer>
  </el-container>
</template>

<script>
import * as echarts from 'echarts';
import axios from "axios";
export default {
  name: "Detail",
  data() {
    return {
      star: 0,
      fork: 0,
      issues: 0,
      watch: 0,
      url: '',
      tagList: [],
      timeList:[],
      currentIndex: 0,
      timer: null,
      add:false,
      password:'',
      passwordVisible:false,
      navList: [
        {name: '/home', navItem: 'Home'}
      ],
      picture: '',
      price: 0,
      detail: '',
      goods_id: 0,
      name:'',
      restNumber:0,
      purchaseNumber:0,
      total_price:0,
      sellerID:'',
      comment:[],
      option: {
        xAxis: {
          data: ['Star', 'Fork', 'Watch', 'Issues']
        },
        yAxis: {},
        series: [
          {
            type: "bar", //形状为柱状图
            data: [
              this.star,
              this.fork,
              this.watch,
              this.issues
            ]
          }
        ],
        color: ['#66FF99'],
      },
      chart: null
    };
  },
  created() {
    this.name = this.$route.query.name
    this.id = this.$route.query.id
    this.fork = this.$route.query.fork
    this.watch = this.$route.query.watch
    this.issues = this.$route.query.issues
    this.star = this.$route.query.star
    this.url = this.$route.query.url
    axios.get('http://localhost:8443//projectTag',{
      params:{ id:this.id }}).then(res =>{
      this.tagList = res.data;
    }).catch(
    )
    axios.get('http://localhost:8443//projectTime',{
      params:{
        id:this.id
      }}).then(res =>{
      this.timeList = res.data;
    }).catch(
    )

  },
  mounted() {
    // this.initChart();
  },
  methods: {
    initChart(){
      this.option.series[0].data[0] = this.star
      this.option.series[0].data[1] = this.fork
      this.option.series[0].data[2] = this.watch
      this.option.series[0].data[3] = this.issues
      this.chart = echarts.init(this.$refs.echarts);
      this.chart.setOption(this.option);
    },

    addCollection() {
      this.add = false

      this.initChart()
      // axios.post('/add_cart', {
      //   goods_id: this.goods_id,
      //   seller_id: this.sellerID,
      //   num: this.purchaseNumber,
      //   price: this.price
      // })
      // .then(res =>{
      //   if (res.data.code === 400) {
      //     this.$message({showClose: true, message: '不能加购自己发布的商品', type: 'error'})
      //   } else {
      //     this.$message({showClose: true, message: '成功加入购物车', type: 'success'})
      //   }
      // })
    },


  }


}
</script>

<style scoped>

div{
  size: 20px;
  /*float: left;*/
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 160px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #d3dce6;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>
