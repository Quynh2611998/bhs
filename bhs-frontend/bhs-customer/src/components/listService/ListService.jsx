import React, { useState } from "react";

function ListService() {
  return (
    <div className="container">
      <div className="row mt-auto">
        <div className="col">
          <button className="btn btn-light">
            <span>DỊCH VỤ</span>
          </button>
        </div>
        <div className="col">
          <button className="btn btn-light">
            <span>TÁC PHẨM</span>
          </button>
        </div>
        <div className="col">
          <button className="btn btn-light">
            <span>ĐÁNH GIÁ</span>
          </button>
        </div>
      </div>
    </div>
  );
}

export default ListService;
