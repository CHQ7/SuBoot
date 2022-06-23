
import open from '@/api/open/open'

/** 商品 */
import Goods from '@/api/goods/goods'
import GoodsCategory from '@/api/goods/category'

/** 订单 */
import Order from '@/api/order/order'

/** 会员 */
import Member from '@/api/member/member'
import MemberLevel from '@/api/member/level'
import MemberChannel from '@/api/member/channel'
import MemberGroup from '@/api/member/group'
import MemberLabel from '@/api/member/label'

/** 财务 */
import FinanceRecharge from '@/api/finance/recharge'
import FinanceBalance from '@/api/finance/balance'
import FinancePoints from '@/api/finance/points'

/** 文章 */
import Article from '@/api/article/article'
import ArticleCategory from '@/api/article/category'

/** 多商户 */
import Merch from '@/api/merch/merch'
import MerchConfig from '@/api/merch/config'

/** 设置 */
import ConfigEmail from '@/api/config/email'
import ConfigPlatform from '@/api/config/platform'
import ConfigBasic from '@/api/config/basic'
import ConfigSms from '@/api/config/sms'
import ConfigSmsTemp from '@/api/config/smsTemp'
import ConfigSmsLog from '@/api/config/smsLog'
import ConfigWx from '@/api/config/wx'
import ConfigPay from '@/api/config/pay'

/** 平台 */
import PlatformAdv from '@/api/platform/adv'
import PlatformFeedback from '@/api/platform/feedback'
import PlatformLink from '@/api/platform/link'
import PlatformNav from '@/api/platform/nav'
import PlatformNotice from '@/api/platform/notice'
import PlatformUpdate from '@/api/platform/update'
import PlatformBanner from '@/api/platform/banner'

/** 系统 */
import SystemLog from '@/api/system/log'
import SystemAuthLog from '@/api/system/authLog'
import SystemMenu from '@/api/system/menu'
import SystemParam from '@/api/system/param'
import SystemRole from '@/api/system/role'
import SystemTask from '@/api/system/task'
import SystemDept from '@/api/system/dept'
import SystemUser from '@/api/system/user'
import SystemFile from '@/api/system/file'

const install = (Vue, vm) => {
  // 将各个定义的接口名称，统一放进对象挂载到vm.$u.api(因为vm就是this，也即this.$u.api)下
  vm.$u.api = {
    open,
    SystemLog,
    SystemAuthLog,
    SystemMenu,
    SystemParam,
    SystemRole,
    SystemTask,
    SystemDept,
    SystemUser,
    SystemFile,

    PlatformAdv,
    PlatformFeedback,
    PlatformLink,
    PlatformNav,
    PlatformNotice,
    PlatformUpdate,
    PlatformBanner,

    ConfigEmail,
    ConfigPlatform,
    ConfigSms,
    ConfigWx,
    ConfigPay,
    ConfigSmsTemp,
    ConfigSmsLog,
    ConfigBasic,

    Article,
    ArticleCategory,

    FinanceRecharge,
    FinanceBalance,
    FinancePoints,

    Member,
    MemberLevel,
    MemberChannel,
    MemberGroup,
    MemberLabel,

    Order,

    Goods,
    GoodsCategory,
    Merch,
    MerchConfig
  }
}

export default {
  install
}
