__d(function(e,t,r,n){Object.defineProperty(n,"__esModule",{value:!0}),n.unpublishedTripTemplatesSelector=n.publishedTripTemplatesSelector=n.unreviewedTripInstancesSelector=n.reviewableTripInstanceIdsSelector=n.reviewsSelector=n.payoutsSelector=n.guestProfilesSelector=n.experienceInstancesSelector=n.experienceTemplatesSelector=n.tripReservationsSelector=n.tripInstancesSelector=n.tripTemplatesSelector=n.feedbackByGuestProfileIdSelector=n.feedbackSelector=n.statsSelector=n.availabilitySelector=n.rootSelector=void 0;var c=t(1471),i=t(1752),o=t(1789),l=n.rootSelector=function(e){return e.cityHostsManager},s=(n.availabilitySelector=function(e){return l(e).availability},n.statsSelector=function(e){return l(e).stats},n.feedbackSelector=function(e){return l(e).feedback}),u=(n.feedbackByGuestProfileIdSelector=(0,c.createSelector)(s,function(e){return e.feedbackByGuestProfileId}),n.tripTemplatesSelector=function(e){return l(e).tripTemplates}),a=n.tripInstancesSelector=function(e){return l(e).tripInstances},p=(n.tripReservationsSelector=function(e){return l(e).tripReservations},n.experienceTemplatesSelector=function(e){return l(e).experienceTemplates},n.experienceInstancesSelector=function(e){return l(e).experienceInstances},n.guestProfilesSelector=function(e){return l(e).guestProfiles},n.payoutsSelector=function(e){return l(e).payouts},n.reviewsSelector=function(e){return l(e).reviews},n.reviewableTripInstanceIdsSelector=function(e){return l(e).reviewableTripInstanceIds}),S=(n.unreviewedTripInstancesSelector=(0,c.createSelector)(a,p,function(e,t){return t.map(function(t){return e[t]})}),(0,c.createSelector)(u,function(e){return Object.values(e)}));n.publishedTripTemplatesSelector=(0,c.createSelector)(S,function(e){return e.filter(function(e){return(0,i.isPublishedTripTemplate)(e)})}),n.unpublishedTripTemplatesSelector=(0,c.createSelector)(S,function(e){return e.filter(function(e){return(0,i.isUnpublishedTripTemplate)(e)&&o.QUEUE_STATUSES_FOR_VIEWING_TEMPLATES.includes(e.queueStatus)})})},1803);