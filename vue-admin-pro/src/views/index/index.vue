<template>
  <div class="dashboard-container">
    <component :is="currentRole" />
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import adminDashboard from './admin'

export default {
  name: 'Dashboard',
  components: { adminDashboard },
  data() {
    return {
      currentRole: 'adminDashboard'
    }
  },
  computed: {
    ...mapGetters([
      'roles'
    ])
  },
  created() {
    if (!this.roles.includes('root')) {
      this.currentRole = 'adminDashboard'
    }
  }
}
</script>
<style scoped>
  .dashboard-container{
    background-color: #f1f1f1;
    min-height: calc(100vh - 84px);
    overflow-x: auto;
  }
</style>
