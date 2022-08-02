export function nFormatter(num, digits) {
  const lookup = [
    { value: 1, symbol: "" },
    { value: 1e3, symbol: "k" },
    { value: 1e6, symbol: "M" },
    { value: 1e9, symbol: "G" },
    { value: 1e12, symbol: "T" },
    { value: 1e15, symbol: "P" },
    { value: 1e18, symbol: "E" },
  ];
  const rx = /\.0+$|(\.[0-9]*[1-9])0+$/;
  let item = lookup
    .slice()
    .reverse()
    .find(function (item) {
      return num >= item.value;
    });
  return item
    ? (num / item.value).toFixed(digits).replace(rx, "$1") + item.symbol
    : "0";
}

export function timeDiff(curr, prev) {
  let ms_Min = 60 * 1000;
  let ms_Hour = ms_Min * 60;
  let ms_Day = ms_Hour * 24;
  let ms_Mon = ms_Day * 30;
  let ms_Yr = ms_Day * 365;
  let diff = curr - prev;

  if (diff < ms_Min) {
    return Math.round(diff / 1000) + ' giây trước';
  } else if (diff < ms_Hour) {
    return Math.round(diff / ms_Min) + ' phút trước';
  } else if (diff < ms_Day) {
    return Math.round(diff / ms_Hour) + ' giờ trước';
  } else if (diff < ms_Mon) {
    return Math.round(diff / ms_Day) + ' ngày trước';
  } else if (diff < ms_Yr) {
    return Math.round(diff / ms_Mon) + ' tuần trước';
  } else {
    return Math.round(diff / ms_Yr) + ' năm trước';
  }
}

