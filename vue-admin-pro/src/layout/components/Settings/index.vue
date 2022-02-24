<template>
  <div class="drawer-container">

    <!--      <h3 class="drawer-title">页面样式设置</h3>-->

    <h3 class="drawer-title">整体风格设置</h3>
    <div class="page-setting">
      <div v-for="(item,index) in pageList" :key="index" class="setting_item" @click="setpage(index)">
        <img :src="item.img" alt="">
        <div v-show="item.active" class="check">
          <i class="el-icon-check" :style="{'color':color}" />
        </div>
      </div>
    </div>

    <h3 class="drawer-title">主题色</h3>
    <div class="theme-color-content">
      <div v-for="(item,index) in themeColorList" :key="index" class="theme-color-block" :style="{'backgroundColor':item.color}" @click="setcolor(index)">
        <div v-show="item.active" class="check">
          <i class="el-icon-check" />
        </div>
      </div>
    </div>

    <h3 class="drawer-title Mode">导航模式</h3>
    <div class="page-setting">
      <div v-for="(item,index) in modeList" :key="index" class="setting_item" @click="setmode(index)">
        <img :src="item.img" alt="">
        <div v-show="item.active" class="check">
          <i class="el-icon-check" :style="{'color':color}" />
        </div>
      </div>
    </div>

    <!--    <div class="drawer-item">-->
    <!--    <span>主题色</span>-->
    <!--      <u-theme-picker style="float: right;height: 26px;margin: -3px 8px 0 0;" :default-theme="$store.state.settings.theme" @change="themeChange" />-->
    <!--    </div>-->

    <div class="drawer-item">
      <span>标签</span>
      <el-switch v-model="tagsView" class="drawer-switch" />
    </div>

    <div class="drawer-item">
      <span>固定 Header</span>
      <el-switch v-model="fixedHeader" class="drawer-switch" />
    </div>

    <div class="drawer-item">
      <span>侧边栏徽标</span>
      <el-switch v-model="sidebarLogo" class="drawer-switch" />
    </div>

  </div>
</template>

<script>
import { mapState } from 'vuex'
import Cookies from 'js-cookie'
export default {
  data() {
    return {
      pageList: [
        {
          img: require('@/assets/svg/page1.svg'),
          active: false
        },
        {
          img: require('@/assets/svg/page2.svg'),
          active: false
        },
        {
          img: require('@/assets/svg/page3.svg'),
          active: false
        }
      ],
      modeList: [
        {
          img: require('@/assets/svg/mode1.svg'),
          active: false
        },
        {
          img: require('@/assets/svg/mode2.svg'),
          active: false
        },
        {
          img: require('@/assets/svg/mode3.svg'),
          active: false
        }
      ],
      themeColorList: [
        {
          color: '#1890ff',
          active: false
        },
        {
          color: '#f5222d',
          active: false
        },
        {
          color: '#fa541c',
          active: false
        },
        {
          color: '#faad14',
          active: false
        },
        {
          color: '#13c2c2',
          active: false
        },
        {
          color: '#52c41a',
          active: false
        },
        {
          color: '#2f54eb',
          active: false
        },
        {
          color: '#722ed1',
          active: false
        }
      ]

    }
  },
  computed: {
    ...mapState({
      color: state => state.app.color,
      page: state => state.app.page,
      mode: state => state.app.mode
    }),
    fixedHeader: {
      get() {
        return this.$store.state.settings.fixedHeader
      },
      set(val) {
        this.$store.dispatch('settings/changeSetting', {
          key: 'fixedHeader',
          value: val
        })
      }
    },
    tagsView: {
      get() {
        return this.$store.state.settings.tagsView
      },
      set(val) {
        this.$store.dispatch('settings/changeSetting', {
          key: 'tagsView',
          value: val
        })
      }
    },
    sidebarLogo: {
      get() {
        return this.$store.state.settings.sidebarLogo
      },
      set(val) {
        this.$store.dispatch('settings/changeSetting', {
          key: 'sidebarLogo',
          value: val
        })
      }
    }
  },
  created() {
    this.themeColorList.forEach((item, index) => {
      if (item.color === this.color) {
        this.themeColorList[index].active = true
      }
    })
    this.pageList[parseInt(this.page)].active = true
    this.modeList[parseInt(this.mode)].active = true
  },
  methods: {
    // 设置主题颜色
    themeChange(val) {
      this.$store.dispatch('settings/changeSetting', {
        key: 'theme',
        value: val
      })
    },
    setpage(i) { // 设置整体风格设置
      this.pageList.forEach((item, index) => {
        if (index === i) {
          this.pageList[i].active = true
          Cookies.set('page', index + '')
          this.$store.dispatch('app/setPage', index + '')
        } else {
          this.pageList[index].active = false
        }
      })
    },
    setcolor(i) { // 设置主题颜色
      this.themeColorList.forEach((item, index) => {
        if (index === i) {
          this.themeColorList[i].active = true
          this.$store.dispatch('app/setColor', item.color)
        } else {
          this.themeColorList[index].active = false
        }
      })
    },
    setmode(i) { // 设置导航模式
      this.modeList.forEach((item, index) => {
        if (index === i) {
          this.modeList[i].active = true
          this.$store.dispatch('app/setMode', index + '')
        } else {
          this.modeList[index].active = false
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
  .page-setting{
    display: flex;
    align-items: center;
    justify-content: flex-start;
    padding-bottom: 10px;
    .setting_item{
      margin-right: 20px;
      position: relative;
      cursor: pointer;
      .check{
        position: absolute;
        top: 17px;
        right: 10px;
        color: #52c41a;
      }
    }
  }
  .theme-color-content{
    display: flex;
    justify-content: flex-start;
    align-items: center;
    padding-bottom: 20px;
    border-bottom: 1px solid #e8e8e8;
    .theme-color-block{
      width: 20px;
      height: 20px;
      margin-right: 8px;
      color: #fff;
      font-weight: bold;
      text-align: center;
      border-radius: 2px;
      cursor: pointer;
    }
  }
 .Mode{
   margin-top: 20px;
 }
.drawer-container {
  padding: 24px;
  padding-top: 0;
  font-size: 14px;
  line-height: 1.5;
  word-wrap: break-word;

  .drawer-title {
    margin-bottom: 12px;
    color: rgba(0, 0, 0, .85);
    font-size: 14px;
    line-height: 22px;
  }

  .drawer-item {
    color: rgba(0, 0, 0, .65);
    font-size: 14px;
    padding: 12px 0;
  }

  .drawer-switch {
    float: right
  }
}
</style>
