__d(function(r,e,a,t){Object.defineProperty(t,"__esModule",{value:!0});var n=e(932),o=e(1130);t.default={getBaseProps:function(r,e){r=o.Helpers.modifyProps(r,e,"errorbar");for(var a=this.getCalculatedValues(r,e),t=a.data,n=a.style,i=a.scale,s=r,l=s.groupComponent,y=s.height,u=s.width,d=s.borderWidth,c={parent:{style:n.parent,scale:i,data:t,height:y,width:u}},g=0,m=t.length;g<m;g++){var p=t[g],h=p.eventKey||g,v=i.x(p.x1||p.x),f=i.y(p.y1||p.y),x={x:v,y:f,scale:i,datum:p,data:t,index:g,groupComponent:l,borderWidth:d,style:this.getDataStyles(p,n.data),errorX:this.getErrors(p,i,"x"),errorY:this.getErrors(p,i,"y")};c[h]={data:x};var D=this.getLabelText(r,p,g);(void 0!==D&&null!==D||r.events||r.sharedEvents)&&(c[h].labels=this.getLabelProps(x,D,n))}return c},getLabelProps:function(r,e,a){var t=r.x,n=r.index,o=r.scale,i=r.errorY,s=i&&Array.isArray(i)?i[0]:i,l=s||r.y,y=this.getLabelStyle(a.labels,r)||{};return{style:y,y:l-(y.padding||0),x:t,text:e,index:n,scale:o,datum:r.datum,data:r.data,textAnchor:y.textAnchor,verticalAnchor:y.verticalAnchor||"end",angle:y.angle}},getErrorData:function(r){if(r.data)return r.data.length<1?(o.Log.warn("This is an empty dataset."),[]):this.formatErrorData(r.data,r);var e=(r.errorX||r.errorY)&&this.generateData(r);return this.formatErrorData(e,r)},getErrors:function(r,e,a){var t={x:"errorX",y:"errorY"},o=r[t[a]];return 0!==o&&((0,n.isArray)(o)?[0!==o[0]&&e[a](o[0]+r[a]),0!==o[1]&&e[a](r[a]-o[1])]:[e[a](o+r[a]),e[a](r[a]-o)])},formatErrorData:function(r,e){if(!r)return[];var a={x:o.Helpers.createAccessor(void 0!==e.x?e.x:"x"),y:o.Helpers.createAccessor(void 0!==e.y?e.y:"y"),errorX:o.Helpers.createAccessor(void 0!==e.errorX?e.errorX:"errorX"),errorY:o.Helpers.createAccessor(void 0!==e.errorY?e.errorY:"errorY")},t=function(r){var e=function(r){return!r||r<0?0:r};return(0,n.isArray)(r)?r.map(function(r){return e(r)}):e(r)},i={x:o.Data.createStringMap(e,"x"),y:o.Data.createStringMap(e,"y")};return r.map(function(r,e){var o=a.x(r),s=a.y(r),l=void 0!==o?o:e,y=void 0!==s?s:r,u=t(a.errorX(r)),d=t(a.errorY(r));return(0,n.assign)({},r,{x:l,y:y,errorX:u,errorY:d},"string"==typeof l?{x:i.x[l],xName:l}:{},"string"==typeof y?{y:i.y[y],yName:y}:{})})},getDomain:function(r,e){var a=o.Domain.getDomainFromProps(r,e);if(a)return o.Domain.padDomain(a,r,e);var t=o.Domain.getDomainFromCategories(r,e);if(t)return o.Domain.padDomain(t,r,e);var n=this.getErrorData(r);if(n.length<1)return o.Scale.getBaseScale(r,e).domain();var i=this.getDomainFromData(r,e,n);return o.Domain.cleanDomain(o.Domain.padDomain(i,r,e),r)},getDomainFromData:function(r,e,a){var t=o.Helpers.getCurrentAxis(e,r.horizontal),i=void 0;"x"===t?i="errorX":"y"===t&&(i="errorY");var s=(0,n.flatten)(a).map(function(r){return r[t]}),l=(0,n.flatten)((0,n.flatten)(a).map(function(r){var e=void 0,a=void 0;return(0,n.isArray)(r[i])?(e=r[i][0]+r[t],a=r[t]-r[i][1]):(e=r[i]+r[t],a=r[t]-r[i]),[e,a]})),y=s.concat(l),u=Math.min.apply(Math,babelHelpers.toConsumableArray(y)),d=Math.max.apply(Math,babelHelpers.toConsumableArray(y));if(u===d){return[0,0===d?1:d]}return[u,d]},getCalculatedValues:function(r){var e=r.theme&&r.theme.errorbar&&r.theme.errorbar.style?r.theme.errorbar.style:{},a=o.Helpers.getStyles(r.style,e,"auto","100%")||{},t=(0,n.assign)(o.Data.getData(r),this.getErrorData(r)),i=o.Data.addEventKeys(r,t),s={x:o.Helpers.getRange(r,"x"),y:o.Helpers.getRange(r,"y")},l={x:this.getDomain(r,"x"),y:this.getDomain(r,"y")};return{data:i,scale:{x:o.Scale.getBaseScale(r,"x").domain(l.x).range(s.x),y:o.Scale.getBaseScale(r,"y").domain(l.y).range(s.y)},style:a}},getDataStyles:function(r,e){var a=(0,n.omit)(r,["x","y","name","errorX","errorY","eventKey"]),t=(0,n.defaults)({},a,e);return o.Helpers.evaluateStyle(t,r)},getLabelText:function(r,e,a){return e.label||(Array.isArray(r.labels)?r.labels[a]:o.Helpers.evaluateProp(r.labels,e))},getLabelStyle:function(r,e){r=r||{};var a=e.datum,t=e.size,i=e.style,s=(0,n.pick)(i,["opacity","fill"]),l=r.padding||.25*t,y=(0,n.defaults)({},r,s,{padding:l});return o.Helpers.evaluateStyle(y,a)||{}}}},1297);