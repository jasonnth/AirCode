__d(function(e,t,n,r){"use strict";function i(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(r,"__esModule",{value:!0});var u=function(){function e(e,t){for(var n=0;n<t.length;n++){var r=t[n];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}return function(t,n,r){return n&&e(t.prototype,n),r&&e(t,r),t}}(),o=t(445),a=function(e){return e&&e.__esModule?e:{default:e}}(o),c=function(){function e(t,n){if(i(this,e),!(0,a.default)(t))throw new Error("UnionSchema requires item schema to be an object.");if(!n||!n.schemaAttribute)throw new Error("UnionSchema requires schemaAttribute option.");this._itemSchema=t;var r=n.schemaAttribute;this._getSchema="function"==typeof r?r:function(e){return e[r]}}return u(e,[{key:"getItemSchema",value:function(){return this._itemSchema}},{key:"getSchemaKey",value:function(e){return this._getSchema(e)}}]),e}();r.default=c},1934);