__d(function(e,r,t,i){"use strict";function n(e){if(e&&e.__esModule)return e;var r={};if(null!=e)for(var t in e)Object.prototype.hasOwnProperty.call(e,t)&&(r[t]=e[t]);return r.default=e,r}Object.defineProperty(i,"__esModule",{value:!0}),i.ItineraryImpressionItineraryPageEvent=void 0;var a=r(412),s=function(e){return e&&e.__esModule?e:{default:e}}(a),o=r(694),u=n(o),p=r(1965),l=n(p),d=r(698),f=n(d);i.ItineraryImpressionItineraryPageEvent={defaultProps:{schema:"com.airbnb.jitney.event.logging.Itinerary:ItineraryImpressionItineraryPageEvent:2.0.0",event_name:"itinerary_impression_itinerary_page",page:"t0",section:"list",operation:1},propTypes:{schema:s.default.string,event_name:s.default.string.isRequired,context:s.default.shape(u.Context.propTypes).isRequired,page:s.default.string.isRequired,section:s.default.string.isRequired,operation:s.default.oneOf(Object.values(f.Operation)).isRequired,schedulable_info:s.default.shape(l.SchedulableInfo.propTypes).isRequired}}},1984);