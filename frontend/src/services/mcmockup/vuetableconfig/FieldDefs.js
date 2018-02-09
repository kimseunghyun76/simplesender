export default [
  {
    name: '__sequence',
    title: '#',
    titleClass: 'text-center',
    dataClass: 'text-center'
  },
  {
    name: 'regDate',
    title: 'Request Date',
    titleClass: 'text-center',
    dataClass: 'text-center',
    callback: 'formatDate|YYYY-MM-DD h:mm:ss a',
    sortField: 'regDate'
  },
  {
    name: 'method',
    title: 'Method',
    callback: 'methodLabel',
    sortField: 'method',
    titleClass: 'text-center',
    dataClass: 'text-center'
  },
  {
    name: 'dstMrn',
    title: 'Dst-MRN',
    titleClass: 'text-left',
    dataClass: 'text-left',
    callback: 'chgulower',
    sortField: 'dstMrn'
  },
  {
    name: 'srcMrn',
    title: 'Src-MRN',
    callback: 'chgulower',
    sortField: 'srcMrn'
  },
  {
    name: 'hexSignedData',
    title: 'Hex',
    callback: 'omitStr'
  },
  {
    name: 'accessToken',
    title: 'TOKEN'
  }
]
