import React from 'react';
import './blueprint.css';

const Blueprint = ({ blueprint, solved = false }) => {
  const craftKey = (index, indx) => {
    return "" + index + indx + new Date().getTime();
  }

  const craftRoom = () => {
    if (blueprint.length === 0) {
      return null;
    }

    return (
      blueprint.map((row, index) => {
        return React.Children.toArray(
          <>
            <div className="grid-row">
              {row.map((column, innerIndex) => {
                if (column === 1) {
                  return (<span key={craftKey(index, innerIndex)} className='grid-item wall'></span>);
                }

                return (<span key={craftKey(index, innerIndex)} className='grid-item no-light'></span>);
              })}
            </div>
          </>
        );
      })
    )
  };

  const craftRoomSolved = () => {
    if (blueprint.length === 0) {
      return null;
    }

    return (
      blueprint.map((row, index) => {
        return React.Children.toArray(
          <>
            <div className="grid-row">
              {row.map((column, innerIndex) => {
                if (column === 1) {
                  return (<span key={craftKey(index, innerIndex)} className='grid-item wall'></span>);
                }

                if (column === 0) {
                  return (<span key={craftKey(index, innerIndex)} className='grid-item lightened'></span>);
                }

                return (<span key={craftKey(index, innerIndex)} className='grid-item bulb'></span>);
              })}
            </div>
          </>
        );
      })
    )
  };

  return (
    <div className="blueprint-container">
      { solved
        ? craftRoomSolved()
        : craftRoom()
      }
    </div>
  )
}

export default Blueprint;
