__d(function(e,t,u,n){"use strict";function r(e){if(e&&e.__esModule)return e;var t={};if(null!=e)for(var u in e)Object.prototype.hasOwnProperty.call(e,u)&&(t[u]=e[u]);return t.default=e,t}Object.defineProperty(n,"__esModule",{value:!0}),n.ReduxMeasurementEvent=void 0;var a=t(412),i=function(e){return e&&e.__esModule?e:{default:e}}(a),d=t(694),s=r(d),o=t(697),f=r(o),l=t(698),p=(r(l),t(699)),m=r(p);n.ReduxMeasurementEvent={defaultProps:{schema:"com.airbnb.jitney.event.logging.Performance:ReduxMeasurementEvent:1.0.0",event_name:"redux_measurement"},propTypes:{schema:i.default.string,event_name:i.default.string.isRequired,context:i.default.shape(s.Context.propTypes).isRequired,measurement_type:i.default.oneOf(Object.values(f.NativeMeasurementType)).isRequired,value:i.default.number.isRequired,app_mode:i.default.oneOf(Object.values(m.NativeModeType)).isRequired,action_type:i.default.string.isRequired,page:i.default.string,information:i.default.objectOf(i.default.string)}}},696);