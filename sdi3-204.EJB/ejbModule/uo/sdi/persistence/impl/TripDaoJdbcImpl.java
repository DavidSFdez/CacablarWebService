package uo.sdi.persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import uo.sdi.model.AddressPoint;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.Waypoint;
import uo.sdi.persistence.TripDao;
import uo.sdi.persistence.util.JdbcTemplate;
import uo.sdi.persistence.util.RowMapper;

public class TripDaoJdbcImpl implements TripDao {

    public class TripMapper implements RowMapper<Trip> {

	@Override
	public Trip toObject(ResultSet rs) throws SQLException {
	    Trip res = new Trip();

	    Waypoint wpt = new Waypoint(rs.getDouble("departure_wpt_lat"),
		    rs.getDouble("departure_wpt_lon"));
	    AddressPoint ap = new AddressPoint(
		    rs.getString("departure_address"),
		    rs.getString("departure_city"),
		    rs.getString("departure_state"),
		    rs.getString("departure_country"),
		    rs.getString("departure_zipcode"), wpt);
	    res.setDeparture(ap);

	    wpt = new Waypoint(rs.getDouble("destination_wpt_lat"),
		    rs.getDouble("destination_wpt_lon"));
	    ap = new AddressPoint(rs.getString("destination_address"),
		    rs.getString("destination_city"),
		    rs.getString("destination_state"),
		    rs.getString("destination_country"),
		    rs.getString("destination_zipcode"), wpt);
	    res.setDestination(ap);

	    res.setArrivalDate(toDate(rs.getTimestamp("arrivalDate")));
	    res.setDepartureDate(toDate(rs.getTimestamp("departureDate")));
	    res.setClosingDate(toDate(rs.getTimestamp("closingDate")));

	    res.setAvailablePax(rs.getInt("availablePax"));
	    res.setMaxPax(rs.getInt("maxPax"));
	    res.setComments(rs.getString("comments"));
	    res.setEstimatedCost(rs.getDouble("estimatedCost"));
	    res.setPromoterId(rs.getLong("promoter_Id"));
	    res.setStatus(TripStatus.values()[rs.getInt("status")]);
	    res.setId(rs.getLong("id"));

	    return res;
	}

	private Date toDate(Timestamp timestamp) throws SQLException {
	    return new Date(timestamp.getTime());
	}

    }

    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Override
    public Long save(Trip dto) {

	jdbcTemplate.execute("TRIP_INSERT", dto.getDeparture().getAddress(),
		dto.getDeparture().getCity(), dto.getDeparture().getState(),
		dto.getDeparture().getCountry(), dto.getDeparture()
			.getZipCode(), dto.getDeparture().getWaypoint()
			.getLat(), dto.getDeparture().getWaypoint().getLon(),

		dto.getDestination().getAddress(), dto.getDestination()
			.getCity(), dto.getDestination().getState(), dto
			.getDestination().getCountry(), dto.getDestination()
			.getZipCode(), dto.getDestination().getWaypoint()
			.getLat(), dto.getDestination().getWaypoint().getLon(),

		dto.getArrivalDate(), dto.getDepartureDate(), dto
			.getClosingDate(), dto.getAvailablePax(), dto
			.getMaxPax(), dto.getEstimatedCost(),
		dto.getComments(), dto.getStatus().ordinal(), dto
			.getPromoterId());
	return jdbcTemplate.getGeneratedKey();
    }

    @Override
    public int update(Trip dto) {
	int salida = -1;

	salida = jdbcTemplate.execute("TRIP_UPDATE", dto.getDeparture()
		.getAddress(), dto.getDeparture().getCity(), dto.getDeparture()
		.getState(), dto.getDeparture().getCountry(), dto
		.getDeparture().getZipCode(), dto.getDeparture().getWaypoint()
		.getLat(), dto.getDeparture().getWaypoint().getLon(),

	dto.getDestination().getAddress(), dto.getDestination().getCity(), dto
		.getDestination().getState(),
		dto.getDestination().getCountry(), dto.getDestination()
			.getZipCode(), dto.getDestination().getWaypoint()
			.getLat(), dto.getDestination().getWaypoint().getLon(),

		dto.getArrivalDate(), dto.getDepartureDate(), dto
			.getClosingDate(), dto.getAvailablePax(), dto
			.getMaxPax(), dto.getEstimatedCost(),
		dto.getComments(), dto.getStatus().ordinal(), dto
			.getPromoterId(),

		dto.getId());

	return salida;
    }

    @Override
    public int delete(Long id) {
	return jdbcTemplate.execute("TRIP_DELETE", id);
    }

    @Override
    public Trip findById(Long id) {
	return jdbcTemplate.queryForObject("TRIP_FIND_BY_ID", new TripMapper(),
		id);
    }

    @Override
    public List<Trip> findAll() {
	return jdbcTemplate.queryForList("TRIP_FIND_ALL", new TripMapper());
    }

    @Override
    public Trip findByPromoterIdAndArrivalDate(Long id, Date arrivalDate) {
	return jdbcTemplate.queryForObject("TRIP_FIND_BY_PROMOTER_AND_ARRIVAL",
		new TripMapper(), id, arrivalDate);
    }

    @Override
    public List<Trip> findAllActive() {
	return jdbcTemplate.queryForList("TRIP_FIND_ALL_ACTIVE",
		new TripMapper());
    }

    @Override
    public List<Trip> findWhenParticipated(Long id) {
	return jdbcTemplate.queryForList("TRIP_FIND_WHEN_PARTICIPATED",
		new TripMapper(), id, id, id);
    }

    @Override
    public List<Trip> findAllActiveToUser(Long idUser) {

	return jdbcTemplate.queryForList("TRIP_FIND_ACTUALES_TO_USER",
		new TripMapper(), idUser);
    }

    @Override
    public Trip findByIdAndPromoter(Long idTrip, Long idUser) {

	return jdbcTemplate.queryForObject("TRIP_FIND_BY_ID_AND_PROMOTER",
		new TripMapper(), idTrip, idUser);
    }

    @Override
    public Long updateTripsStatusToClose() {
	jdbcTemplate.execute("TRIP_UPDATE_STATUS");
	return jdbcTemplate.getGeneratedKey();
    }

    @Override
    public List<Trip> findAllPromoted(Long id) {
	return jdbcTemplate.queryForList("TRIP_FIND_PROMOTED",
		new TripMapper(), id);
    }

    @Override
    public List<Trip> findAllParticipated(Long id) {
	return jdbcTemplate.queryForList("TRIPS_FIND_PARTICIPATED",
		new TripMapper(), id);
    }

    @Override
    public List<Trip> findAllPromotedAndActive(long idUser) {
	return jdbcTemplate.queryForList("TRIP_FIND_ALL_PROMOTED_AND_ACTIVE",
		new TripMapper(), idUser);
    }

}
