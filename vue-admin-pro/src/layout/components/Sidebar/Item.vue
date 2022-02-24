<script>
export default {
  name: 'MenuItem',
  functional: true,
  props: {
    icon: {
      type: String,
      default: ''
    },
    title: {
      type: String,
      default: ''
    },
    color: {
      type: String,
      default: ''
    }
  },
  render(h, context) {
    const { icon, title, color } = context.props
    const vnodes = []
    if (icon) {
      const elHtml = h('u-icon-svg',
        {
          style: {
            color: color
          },
          attrs: {
            'name': icon
          }
        })

      vnodes.push(elHtml)
    }

    if (title) {
      const elHtml = h('span', {
        style: {
          color: color
        },
        attrs: {
          slot: 'title'
        }
      }, title)
      vnodes.push(elHtml)
    }
    return vnodes
  }
}
</script>
