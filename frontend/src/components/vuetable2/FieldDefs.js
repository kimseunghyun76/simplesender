export default [
  {
    name: '__sequence',
    title: '#',
    titleClass: 'center aligned',
    dataClass: 'center aligned'
  },
  {
    name: 'regDate',
    title: '일시',
    titleClass: 'center aligned',
    dataClass: 'center aligned',
    callback: 'formatDate|YYYY-MM-DD h:mm:ss a',
    sortField: 'regDate'
  },
  {
    name: 'dstMrn',
    title: 'Dst',
    titleClass: 'text-left',
    dataClass: 'text-left',
    callback: 'chgulower',
    sortField: 'dstMrn'
  },
  {
    name: 'srcMrn',
    title: 'Src',
    callback: 'chgulower',
    sortField: 'srcMrn'
  },
  {
    name: 'method',
    title: 'Method',
    callback: 'methodLabel',
    sortField: 'method'
  },
  {
    name: 'hexSignedData',
    title: 'Hex',
    callback: 'omitStr'
  },
  {
    name: 'accessToken',
    title: 'Token'
  },
  {
    name: 'payload',
    title: 'payload',
    visible: false
  },
  {
    name: '__component:custom-actions',
    title: 'Actions',
    titleClass: 'center aligned',
    dataClass: 'center aligned'
  }
]
