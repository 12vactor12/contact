const menu = {
    list() {
        return [
                   {
                       "backMenu": [
                           {
                               "child": [
                                   {
                                       "buttons": [
                                           "新增",
                                           "查看",
                                           "修改",
                                           "删除"
                                       ],
                                       "menu": "通讯录",
                                       "menuJump": "列表",
                                       "tableName": "ab"
                                   }
                               ],
                               "menu": "通讯录"
                           }
                       ],
                       "frontMenu": []
                   }
               ]
    }
}
export default menu;
