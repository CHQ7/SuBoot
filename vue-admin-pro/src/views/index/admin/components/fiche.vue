<template>
  <div>
    <div v-for="(item,index) in surveyList" v-show="item.data.show" :key="index" class="fiche">
      <div class="fiche-box" :class="item.type">
        <div class="describe">
          <span class="mall-title">{{ item.title }}</span>
          <span v-if="item.data.Previewurl || !item.show" class="mall-btn">
            <a :href="item.data.Previewurl || ''" target="_blank">去查看</a>
          </span>
        </div>
        <div class="icon">
          <span :class="item.icon" />
        </div>
        <span v-if="item.show && item.data.AdministerUrl" class="btn"><a :href="item.data.AdministerUrl" target="_blank">去管理</a></span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Fiche',
  props: {
    surveyList: {
      type: Array,
      default: () => {
        return []
      }
    }
  },
  data() {
    return {}
  }
}
</script>

<style lang="scss" scoped>
.fiche{
  margin: 5px 5px 5px 7px;
  color: #FFF;
  float: left;
  .fiche-box{
    border-radius: 5px;
    overflow: hidden;
    padding:5px 15px;
    max-width: 270px;
    min-width: 204px;
    min-height: 94px;
    max-height: 110px;
    cursor: pointer;
    position: relative;
    &:hover,&:active{
      box-shadow:5px 5px 5px 5px #f4f4f4;
    }
    .describe{
      width: 65%;
      height: 100%;
      text-align: center;
      padding: 10px 0;
      display: grid;
      align-items: center;
      span{
        display: block;
      }
      .mall-title{
        font-weight: bold;
        font-size: 16px;
      }
      .mall-btn{
        font-size: 12px;
        font-weight: 400;
        height: 26px;
        width: 70px;
        line-height: 26px;
        border-radius: 12px;
        border: 1px solid #FFF;
        margin: 10px auto 10px;
      }
      .domain{
        font-size: 13px;
      }
    }
    .btn{
      font-size: 12px;
      border: 1px solid #FFF;
      position: absolute;
    }
    .describe,.icon{
      float: left;
      position: relative;
    }
    .icon{
      width: 35%;
      height: 100%;
      display: flex;
      align-items: center;
    }
  }
}
/* pc */
.pc{
  background: linear-gradient(90deg,rgba(75,154,252,1),rgba(111,200,255,1));
}
.pc-icon{
  display: inline-block;
  position: relative;
  float: right;
  z-index: 2;
  top: -4px;
  width: 60px;
  height: 56px;
  background: url(../../../../assets/img/mbg.png) no-repeat -339px 0;
}
/* 手机 */
.phone{
  background: linear-gradient(90deg,rgba(255,90,68,1),rgba(255,155,115,1));
}
.phone-icon{
  display: inline-block;
  position: relative;
  float: right;
  z-index: 2;
  top: -4px;
  width: 60px;
  height: 56px;
  background: url(../../../../assets/img/mbg.png) no-repeat -219px 0;
}

/* 小程序 */
.wxapp{
  background: linear-gradient(90deg,rgba(7,193,96,1),rgba(110,217,132,1));
}
.wxapp-icon{
  display: inline-block;
  position: relative;
  float: right;
  z-index: 2;
  top: -4px;
  width: 60px;
  height: 56px;
  background: url(../../../../assets/img/mbg.png) no-repeat -90px 0;
}

/* 微信公众号 */
.wx{
  background: linear-gradient(90deg,rgba(007,185,006,1),rgba(119,210,118,1));
}
.wx-icon{
  display: inline-block;
  position: relative;
  float: right;
  z-index: 2;
  top: -4px;
  width: 60px;
  height: 56px;
  background: url(../../../../assets/img/wx.png) no-repeat;
  background-size: cover;
}

/* PC联盟 */
.PcUnion{
  background: linear-gradient(90deg,rgba(001,092,187,1),rgba(107,166,228,1));
}
.PcUnion-icon{
  display: inline-block;
  position: relative;
  float: right;
  z-index: 2;
  top: -4px;
  width: 60px;
  height: 60px;
  background: url(../../../../assets/img/shop1.png) no-repeat;
  background-size: cover;
}

/* 手机联盟 */
.PhoneUnion{
  background: linear-gradient(90deg,rgba(255,136,077,1),rgba(247,188,160,1));
}
.PhoneUnion-icon{
  display: inline-block;
  position: relative;
  float: right;
  z-index: 2;
  top: -4px;
  width: 60px;
  height: 60px;
  background: url(../../../../assets/img/phoneShop.png) no-repeat;
  background-size: cover;
}
@media screen and (min-width:1680px) and (max-width: 1980px){
  .fiche-box {
    width: 226px;
    height: 110px;
    border-radius: 12px;
  }
  .btn{
    right: 35px;
    bottom: 4px;
  }
}
@media screen and (min-width:1380px)  and (max-width:  1680px){
  .fiche-box {
    width: 210px;
    height: 105px;
    border-radius: 8px;
  }
  .btn{
    right: 32px;
    bottom: 4px;
  }
}
@media screen and (max-width:  1380px){
  .fiche-box {
     width: 204px;
    height: 94px;
    border-radius: 5px;
  }
  .btn{
    right: 28px;
    bottom: 2px;
  }
}
</style>
