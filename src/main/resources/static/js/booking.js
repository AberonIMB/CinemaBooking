const maxSelection = 5;
const selectedSeats = new Set();

const seats = document.querySelectorAll('.seat:not(.booked)');
const selectedInfo = document.getElementById('selected-info');
const bookingForm = document.getElementById('booking-form');
const selectedSeatsInput = document.getElementById('selectedSeatsInput');
const submitBtn = document.getElementById('submitBooking');

seats.forEach(seat => {

  seat.addEventListener('mouseenter', () => {
      if (!seat.classList.contains('booked')) {
        seat.textContent = seat.dataset.seat; // показываем номер ряда
      }
    });
    seat.addEventListener('mouseleave', () => {
      seat.textContent = ''; // убираем цифру, возвращаем пустое кружок
    });

  seat.style.cursor = 'pointer';

  seat.addEventListener('click', () => {
    const seatId = seat.getAttribute('id');

    if (seat.classList.contains('selected')) {
      seat.classList.remove('selected');
      selectedSeats.delete(seatId);
    } else {
      if (selectedSeats.size < maxSelection) {
        seat.classList.add('selected');
        selectedSeats.add(seatId);
      } else {
        alert(`Можно выбрать максимум ${maxSelection} мест`);
        return;
      }
    }

    updateUI();
  });
});

function updateUI() {
  if (selectedSeats.size === 0) {
    selectedInfo.textContent = 'Выберите места.';
    bookingForm.style.display = 'none';
  } else {
    let html = `<strong>Выбрано мест (${selectedSeats.size}):</strong><ul>`;
    selectedSeats.forEach(id => {
      const seatEl = document.getElementById(id);
      if (seatEl) {
        const row = seatEl.getAttribute('data-row');
        const seatNumber = seatEl.getAttribute('data-seat');
        html += `<li>Ряд ${row}, Место ${seatNumber}</li>`;
      }
    });
    html += '</ul>';
    selectedInfo.innerHTML = html;

    bookingForm.style.display = 'block';
  }

  selectedSeatsInput.value = Array.from(selectedSeats).join(',');
}