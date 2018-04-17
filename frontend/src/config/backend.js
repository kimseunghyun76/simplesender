export default {
  restapi: {
    metrics: 'http://192.168.11.159:7090/metrics',
    mccmsgcode: 'http://192.168.11.159:7090/MccMsgcode',
    mcclogdelete: 'http://192.168.11.159:7090/MccLogDelete',
    mccloglist: 'http://192.168.11.159:7090/MccLogList',
    queuelist: 'http://192.168.11.159:7090/queuelist',
    clientresponsedelete: 'http://192.168.11.159:7090/clientResponseDelete',
    clientResponseList: 'http://192.168.11.159:7090/clientResponseList',
    svlist: 'http://192.168.11.159:7090/svlist',
    svpath: 'http://192.168.11.159:7090/path',
    svfile: 'http://192.168.11.159:7090/svfile'
  },
  process: {
    status: 'http://192.168.11.159:7090/status',
    pstart: 'http://192.168.11.159:7090/pstart',
    pstop: 'http://192.168.11.159:7090/pstop'
  },
  mockup: {
    defaulttarget: 'http://143.248.55.83:8088'
  },
  linkitem: [
    { isActive: true, name: 'MC Logviewer', link: 'http://192.168.11.159:7090/index.html', details: '계정정보 : 관리자에게 문의' },
    { isActive: true, name: 'MCC Gateway', link: 'http://192.168.11.159:7088/', details: '계정정보 :관리자에게 문의' },
    { isActive: true, name: 'Kaist MMS Monitor', link: 'https://mms-kaist.com/', details: '계정정보 : wowkiller@neonexsoft.com / qwe123!@#' },
    { isActive: true, name: 'RabbitMQ Server', link: 'http://192.168.11.159:15672/', details: '계정정보 : guest/guest , neonex/neonex' },
    { isActive: true, name: 'CI Server(Jenkins)', link: 'http://192.168.11.159:8080/', details: '계정정보 : admin / 1212' },
    { isActive: true, name: 'Elasticsearch', link: 'http://192.168.11.159:9200/', details: '' },
    { isActive: true, name: 'Kibana', link: 'http://192.168.11.159:5601/', details: '' },
    { isActive: true, name: 'Smart PMS', link: 'https://pms.krenav.org/', details: '계정정보 :관리자에게 문의 (Project Management System)' },
    { isActive: true, name: 'GitLab', link: 'https://gitlab.neonexsoft.com/e-navi', details: '계정정보 :관리자에게 문의' }
  ]
}
