__d(function(e,t,n,r){function l(e){return null!=e.schema?c.logJitneyEvent(e):c.logEvent(e),Promise.resolve()}var o=t(42),a=babelHelpers.interopRequireDefault(o),u=t(686),d=babelHelpers.interopRequireDefault(u),i=t(711),c=a.default.module({moduleName:"TrackingBridge",mock:{logEvent:function(){}}});d.default.setLogger({request:l}),d.default.addContext({rendered_on:"react_native"}),(0,i.addTrackingContext)()},710);