__d(function(e,t,r,n){function a(e,t,r){return(0,l.handle)(t,r,{start:function(r){return babelHelpers.extends({},r,babelHelpers.defineProperty({},e,babelHelpers.extends({},t[e],{isFetching:!0})))},failure:function(r){return babelHelpers.extends({},r,babelHelpers.defineProperty({},e,babelHelpers.extends({},t[e],{isFetching:!1,fetchError:!0})))},success:function(n){return babelHelpers.extends({},n,babelHelpers.defineProperty({},e,babelHelpers.extends({},t[e],{isFetching:!1,fetchError:!1,transactions:t[e].transactions.concat(r.payload.transactions),moreToLoad:10===r.payload.transactions.length,emptyTransactions:0===r.payload.transactions.length&&0===t[e].transactions.length})))}})}function s(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:d,t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{};switch(t.type){case i.COMPLETED_TRANSACTIONS_FETCH:return a(c.TRANSACTION_TYPES.COMPLETED,e,t);case i.UPCOMING_TRANSACTIONS_FETCH:return a(c.TRANSACTION_TYPES.FUTURE,e,t);case i.TRANSACTIONS_PAGE_RESET:return d;default:return e}}Object.defineProperty(n,"__esModule",{value:!0});var o,l=t(667),i=t(2136),c=t(2139),T={isFetching:!1,fetchError:!1,transactions:[],moreToLoad:!0,emptyTransactions:!1},d=(o={},babelHelpers.defineProperty(o,c.TRANSACTION_TYPES.COMPLETED,T),babelHelpers.defineProperty(o,c.TRANSACTION_TYPES.FUTURE,T),o);n.default=s},2138);