__d(function(t,e,n,r){Object.defineProperty(r,"__esModule",{value:!0}),r.victoryInterpolator=r.interpolateFunction=r.interpolateImmediate=r.isInterpolatable=void 0;var o=e(656),i=function(t){return t&&t.__esModule?t:{default:t}}(o),u=e(1079),a=r.isInterpolatable=function(t){if(null!==t)switch(typeof t){case"undefined":return!1;case"number":return!isNaN(t)&&t!==Number.POSITIVE_INFINITY&&t!==Number.NEGATIVE_INFINITY;case"string":return!0;case"boolean":return!1;case"object":return t instanceof Date||Array.isArray(t)||(0,i.default)(t);case"function":return!0}return!1},c=r.interpolateImmediate=function(t,e){var n=arguments.length>2&&void 0!==arguments[2]?arguments[2]:0;return function(r){return r<n?t:e}},f=r.interpolateFunction=function(t,e){return function(n){return n>=1?e:function(){var r="function"==typeof t?t.apply(this,arguments):t,o="function"==typeof e?e.apply(this,arguments):e;return(0,u.interpolate)(r,o)(n)}}};r.victoryInterpolator=function(t,e){return t!==e&&a(t)&&a(e)?"function"==typeof t||"function"==typeof e?f(t,e):(0,u.interpolate)(t,e):c(t,e)}},1132);