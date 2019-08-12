__d(function(e,t,a,n){function r(e,t,a,n){return"."+String(e)+"__"+String(t)+"__"+String(a)+"__"+String(n)}function i(e){return Object.keys(e).reduce(function(t,a){var n=e[a];return t[a]="object"==typeof n?JSON.stringify(n):String(n),t},{})}function s(e,t,a,n){requestIdleCallback(function(){var r=Math.round(e-t.startTime),s=babelHelpers.extends({},t.properties,a.properties,{value:r,status:n}),p=i(s);c.default.logEvent({event_name:"perf_logger",event_data:babelHelpers.extends({operation:a.event},s)}),c.default.logJitneyEvent({schema:o.MobilePerfGenericEvent,event_data:{perf_operation:a.event,total_time_ms:r,perf_status:y[n],perf_info:p}}),c.default.logJitneyEvent({schema:l.NativeMeasurementEvent,event_data:{measurement_type:a.measureType,native_measurement_operation:a.event,value:r,app_mode:d.NativeModeType.Unknown,view:a.view,information:p}})})}var o=(babelHelpers.taggedTemplateLiteral(["\n        An event with the same key has already been started. This is likely to cause incorrect\n        times to be logged. Consider passing in a 'tag' property to uniquely identify the event.\n        The duplicated key that was found was \"",'".\n      '],["\n        An event with the same key has already been started. This is likely to cause incorrect\n        times to be logged. Consider passing in a 'tag' property to uniquely identify the event.\n        The duplicated key that was found was \"",'".\n      ']),babelHelpers.taggedTemplateLiteral(['\n          Attempted to mark a PerformanceLogger event completed, but did not find the corresponding\n          start time. Make sure that you are running `markStart(...)` as well, and are passing in\n          the same key. The key used was "','".\n        '],['\n          Attempted to mark a PerformanceLogger event completed, but did not find the corresponding\n          start time. Make sure that you are running \\`markStart(...)\\` as well, and are passing in\n          the same key. The key used was "','".\n        ']),t(1450)),l=t(1452),d=t(700),p=t(1451),u=t(686),c=babelHelpers.interopRequireDefault(u),v=t(418),m=(babelHelpers.interopRequireDefault(v),t(1453)),f=(babelHelpers.interopRequireDefault(m),t(702)),g=babelHelpers.interopRequireDefault(f),y={completed:p.PerfStatus.Completed,cancelled:p.PerfStatus.Cancelled},h=function(){function e(){babelHelpers.classCallCheck(this,e),this.trackedEvents={}}return e.prototype.markStart=function(e){var t=r(e.event,e.tag,e.view,e.measureType);this.trackedEvents[t]={startTime:(0,g.default)(),properties:e.properties}},e.prototype.clearMark=function(e){var t=r(e.event,e.tag,e.view,e.measureType);delete this.trackedEvents[t]},e.prototype.markCompleted=function(e){var t=(0,g.default)(),a=r(e.event,e.tag,e.view,e.measureType),n=this.trackedEvents[a];n&&(delete this.trackedEvents[a],s(t,n,e,"completed"))},e.prototype.markCancelled=function(e){var t=(0,g.default)(),a=r(e.event,e.tag,e.view,e.measureType),n=this.trackedEvents[a];n&&(delete this.trackedEvents[a],s(t,n,e,"cancelled"))},e}();h.sharedInstance=new h,a.exports=h},1449);