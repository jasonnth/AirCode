__d(function(t,e,n,i){"use strict";function r(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(i,"__esModule",{value:!0});var u=function(){function t(t,e){for(var n=0;n<e.length;n++){var i=e[n];i.enumerable=i.enumerable||!1,i.configurable=!0,"value"in i&&(i.writable=!0),Object.defineProperty(t,i.key,i)}}return function(e,n,i){return n&&t(e.prototype,n),i&&t(e,i),e}}(),a=function(){function t(e){var n=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{};if(r(this,t),!e||"string"!=typeof e)throw new Error("A string non-empty key is required");this._key=e,this._assignEntity=n.assignEntity;var i=n.idAttribute||"id";this._getId="function"==typeof i?i:function(t){return t[i]},this._idAttribute=i,this._meta=n.meta,this._defaults=n.defaults}return u(t,[{key:"getAssignEntity",value:function(){return this._assignEntity}},{key:"getKey",value:function(){return this._key}},{key:"getId",value:function(t,e){return this._getId(t,e)}},{key:"getIdAttribute",value:function(){return this._idAttribute}},{key:"getMeta",value:function(t){if(!t||"string"!=typeof t)throw new Error("A string non-empty property name is required");return this._meta&&this._meta[t]}},{key:"getDefaults",value:function(){return this._defaults}},{key:"define",value:function(t){for(var e in t)t.hasOwnProperty(e)&&(this[e]=t[e])}}]),t}();i.default=a},1932);