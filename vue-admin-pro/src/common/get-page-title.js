import defaultSettings from '@/config/settings'

/**
 * 设置网站标题
 * @type {string}
 */
const title = defaultSettings.title

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}
