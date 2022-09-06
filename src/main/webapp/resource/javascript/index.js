
function confirmDelete(event) {
	return confirm("Xác nhận ?") ? '' : event.preventDefault();
}
function checkDate(event) {
	let from = new Date(document.getElementById("from").value);
	let to = new Date(document.getElementById("to").value);
	if (from > to) {
		alert("Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc");
		event.preventDefault();
	}
}