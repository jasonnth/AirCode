__d(function(t,e,o,r){function a(t){return"/"+c+"/"+t}function i(t,e){return"/"+c+"/"+t+"/"+e}Object.defineProperty(r,"__esModule",{value:!0}),r.base=void 0,r.cityPath=a,r.topCategoryPath=i;var n=e(2043),s=babelHelpers.interopRequireDefault(n),u=e(2044),y=(0,s.default)(),c=r.base="things-to-do";y.set("/"+c+"/rooms/:listingId",u.LISTING),y.set("/"+c+"/:city/:topCategory/:subCategory",u.SUB_CATEGORY),y.set("/"+c+"/:city/:topCategory",u.TOP_CATEGORY),y.set("/"+c+"/:city",u.CITY),r.default=y},2042);